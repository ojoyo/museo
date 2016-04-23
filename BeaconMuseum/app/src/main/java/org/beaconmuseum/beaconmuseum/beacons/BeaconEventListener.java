package org.beaconmuseum.beaconmuseum.beacons;

import com.google.inject.Singleton;
import com.kontakt.sdk.android.ble.discovery.*;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.common.profile.*;

import org.beaconmuseum.beaconmuseum.BeaconInfo;

import java.util.*;

@Singleton
public class BeaconEventListener implements ProximityManager.ProximityListener {
    private static Vector<BeaconEventProcessorInterface> processorsList = new Vector<>();

    @Override
    public void onScanStart() {}

    @Override
    public void onScanStop() {}

    @Override
    public void onEvent(BluetoothDeviceEvent bluetoothDeviceEvent) {
        List<? extends RemoteBluetoothDevice> deviceList = bluetoothDeviceEvent.getDeviceList();

        for(RemoteBluetoothDevice device: deviceList)
            for(BeaconEventProcessorInterface processor: processorsList) {
                BeaconInfo beacon = new BeaconInfo(device);
                EventType eventType = bluetoothDeviceEvent.getEventType();

                processor.processBeaconEvent(eventType, beacon);
            }
    }

    public void registerProcessor(BeaconEventProcessorInterface processor) {
        processorsList.add(processor);
    }
}
