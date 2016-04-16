package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.*;
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
    public void onScanStart() {}

    @Override
    public void onScanStop() {}

    @Override
    public void onEvent(BluetoothDeviceEvent bluetoothDeviceEvent) {
        List<? extends RemoteBluetoothDevice> deviceList = bluetoothDeviceEvent.getDeviceList();

        for(RemoteBluetoothDevice device : deviceList)
            processEvent(bluetoothDeviceEvent.getEventType(), device);
    }

    private void processEvent(EventType event, RemoteBluetoothDevice device) {
        BeaconsInRangeList beaconsList = BeaconsInRangeList.getInstance();
        BeaconInfo beacon = new BeaconInfo(
                device.getUniqueId(),
                device.getName(),
                device.getDistance()
        );

        switch (event) {
            case DEVICE_DISCOVERED:
                beaconsList.removeBeaconFromList(beacon);
                beaconsList.addBeaconToList(beacon);
                break;
            case DEVICES_UPDATE:
                beaconsList.removeBeaconFromList(beacon);
                beaconsList.addBeaconToList(beacon);
                break;
            case DEVICE_LOST:
                beaconsList.removeBeaconFromList(beacon);
                break;
        }
    }
}
