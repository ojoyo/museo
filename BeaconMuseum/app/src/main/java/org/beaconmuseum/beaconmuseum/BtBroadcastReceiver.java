package org.beaconmuseum.beaconmuseum;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by czarny on 23.05.16.
 */
public class BtBroadcastReceiver extends BroadcastReceiver {

    private BluetoothAdapter btAdapter;

    public BtBroadcastReceiver() {
        super();
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String stateExtra = BluetoothAdapter.EXTRA_STATE;
        int state = intent.getIntExtra(stateExtra, -1);
        String toastText;
        switch (state) {
            case BluetoothAdapter.STATE_ON:
                toastText = "Bluetooth on";
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
                break;
            case BluetoothAdapter.STATE_OFF:
                toastText = "Bluetooth off";
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void checkBluetoothConnection(Activity activity) {
        if (btAdapter == null)
            return;

        String actionStateChanged = BluetoothAdapter.ACTION_STATE_CHANGED;
        IntentFilter filter = new IntentFilter(actionStateChanged);
        activity.registerReceiver(this, filter);

        if (!btAdapter.isEnabled()) {
            String actionRequestEnable = BluetoothAdapter.ACTION_REQUEST_ENABLE;
            activity.startActivityForResult(new Intent(actionRequestEnable), 0);
        }
    }
}
