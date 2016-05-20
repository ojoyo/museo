package org.beaconmuseum.beaconmuseum.beacons;

import com.google.inject.Singleton;
import com.kontakt.sdk.android.ble.discovery.EventType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Trzyma listę beaconów będących w zasięgu.
 */
@Singleton
public class BeaconsInRangeList implements BeaconEventProcessorInterface {
    private Map<String, BeaconInfo> beacons = new HashMap<>();

    /**
     * Daje dostęp do instancji singletona.
     *
     * @return obiekt singletona BeaconsInRangeList.
     */
    public BeaconsInRangeList() {}

    /**
     * Pozwala uzyskać dostęp do listy beaconów w zasięgu.
     *
     * @return tablica obiektów typu BeaconInfo
     */
    public BeaconInfo[] getList() {
        return beacons.values().toArray(new BeaconInfo[0]);
    }

    public void clearList() {
        beacons.clear();
    }

    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        switch (event) {
            case DEVICE_DISCOVERED:
                beacons.remove(beacon.id);
                beacons.put(beacon.id, beacon);
                break;
            case DEVICES_UPDATE:
                beacons.remove(beacon.id);
                beacons.put(beacon.id, beacon);
                break;
            case DEVICE_LOST:
                beacons.remove(beacon.id);
                break;
        }
    }
}
