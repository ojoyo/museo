package org.beaconmuseum.beaconmuseum.beacons;

import com.kontakt.sdk.android.ble.discovery.EventType;

public interface BeaconEventProcessorInterface {
    public void processBeaconEvent(EventType event, BeaconInfo beacon);
}
