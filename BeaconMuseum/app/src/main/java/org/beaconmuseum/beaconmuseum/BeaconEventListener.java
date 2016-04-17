package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.*;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.common.profile.*;
import java.util.*;

public class BeaconEventListener implements ProximityManager.ProximityListener {
    private static BeaconEventListener ourInstance = new BeaconEventListener();
    private static Vector<BeaconEventProcessorInterface> processorsList = new Vector<>();

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

        for(RemoteBluetoothDevice device: deviceList)
            for(BeaconEventProcessorInterface processor: processorsList)
                processor.processBeaconEvent(bluetoothDeviceEvent.getEventType(), device);
    }

    public void registerProcessor(BeaconEventProcessorInterface processor) {
        processorsList.add(processor);
    }
}
