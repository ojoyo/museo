package org.beaconmuseum.beaconmuseum.beacons;

import com.kontakt.sdk.android.ble.discovery.EventType;

public interface BeaconEventProcessorInterface {
    void processBeaconEvent(EventType event, BeaconInfo beacon);
}
