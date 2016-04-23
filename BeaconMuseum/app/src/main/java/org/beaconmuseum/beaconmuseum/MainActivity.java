package org.beaconmuseum.beaconmuseum;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.beaconmuseum.beaconmuseum.beacons.BeaconManager;

public class MainActivity extends AppCompatActivity {

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

        BeaconManager beaconManager = new BeaconManager();
        beaconManager.initialize(this);
    }

    private void checkBluetoothConnection() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!btAdapter.isEnabled()) {
            String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
            String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
            IntentFilter filter = new IntentFilter(actionStateChanged);
            registerReceiver(bluetoothState, filter);
            startActivityForResult(new Intent(actionRequestEnable), 0);
        }
    }

    public void refreshClick(View v) {
        BeaconInfo[] bList = AppManager.refreshGUI();
        TextView textViewBig = (TextView)findViewById(R.id.textView);
        for (int i = 0; i < bList.length; i++)
            if (i == 0)
                textViewBig.setText(bList[i].id);
            //TODO else inne beacony w dynamicznej liscie pod spodem (na razie jest statyczna!)
            // uzywac funkcji GUIManagera
    }
}
