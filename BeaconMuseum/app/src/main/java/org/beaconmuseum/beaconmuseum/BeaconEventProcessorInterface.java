package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.common.profile.RemoteBluetoothDevice;

public interface BeaconEventProcessorInterface {
    void processBeaconEvent(EventType event, BeaconInfo beacon);
}
