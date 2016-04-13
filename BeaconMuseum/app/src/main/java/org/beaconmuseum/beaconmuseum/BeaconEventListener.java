package org.beaconmuseum.beaconmuseum;

import android.util.Log;
import com.kontakt.sdk.android.ble.discovery.BluetoothDeviceEvent;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.common.profile.*;

import java.util.List;

public class BeaconEventListener implements ProximityManager.ProximityListener {
    private static BeaconEventListener ourInstance = new BeaconEventListener();

    public static BeaconEventListener getInstance() {
        return ourInstance;
    }

    private BeaconEventListener() {}

    @Override
    public void onScanStart() {
        // TODO
    }

    @Override
    public void onScanStop() {
        // TODO
    }

    @Override
    public void onEvent(BluetoothDeviceEvent bluetoothDeviceEvent) {
        // TODO
    }
}
