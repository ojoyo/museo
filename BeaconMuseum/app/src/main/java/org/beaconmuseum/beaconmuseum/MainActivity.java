package org.beaconmuseum.beaconmuseum;

import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.google.inject.Inject;
import com.kontakt.sdk.android.ble.discovery.EventType;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import org.beaconmuseum.beaconmuseum.beacons.*;

public class MainActivity extends RoboActivity implements BeaconEventProcessorInterface {
    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Inject BeaconManager beaconManager;
    @Inject AppManager appManager;
    @Inject BeaconEventListener eventListener;
    private BluetoothAdapter btAdapter;

    BroadcastReceiver bluetoothState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(stateExtra, -1);
            String toastText = "";
            switch (state) {
                case BluetoothAdapter.STATE_ON:
                    toastText = "Bluetooth on";
                    Toast.makeText (MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_OFF:
                    toastText = "Bluetooth off";
                    Toast.makeText (MainActivity.this, toastText, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBluetoothConnection();

        beaconManager.initialize(this);
        eventListener.registerProcessor(this);
    }

    private void checkBluetoothConnection() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null)
            return;

        if (!btAdapter.isEnabled()) {
            String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
            String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
            IntentFilter filter = new IntentFilter(actionStateChanged);
            registerReceiver(bluetoothState, filter);
            startActivityForResult(new Intent(actionRequestEnable), 0);
        }
    }

    @Override
    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        refreshClick(null);
    }

    public void refreshClick(View v) {
        BeaconInfo[] bList = appManager.refreshGUI();
        TextView textViewBig = (TextView)findViewById(R.id.textView);
        if(textViewBig == null)
            return;

        for (int i = 0; i < bList.length; i++) {
            Log.d("rangelist", bList[i].id + ", odleglosc: " + bList[i].range + "\n");
            if (i == 0)
                textViewBig.setText(bList[i].id);
            //TODO else inne beacony w dynamicznej liscie pod spodem (na razie jest statyczna!)
            // uzywac funkcji GUIManagera
        }
        Log.d("rangelist", "____\n");

    }

    public void changeVIewTEst(View v) {
        setContentView(R.layout.calibrator_assistent);

        BeaconInfo[] bList = appManager.refreshGUI();
        String[] bNamesList = new String[bList.length];
        for (int i = 0; i < bList.length; i++) {
            bNamesList[i] = bList[i].id;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, bNamesList);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listView.isItemChecked(position))
                    listView.setItemChecked(position, false);
                else
                    listView.setItemChecked(position, true);

            }
        });

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
