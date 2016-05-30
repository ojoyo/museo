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

    private static boolean isInstance = false;
    static BeaconsInRangeList singleton = new BeaconsInRangeList();
    /**
     * Daje dostęp do instancji singletona.
     *
     * @return obiekt singletona BeaconsInRangeList.
     */
    public BeaconsInRangeList() {}

    public static BeaconsInRangeList getInstance() {
        return singleton;
    }

    /**
     * Pozwala uzyskać dostęp do listy beaconów w zasięgu.
     *
     * @return tablica obiektów typu BeaconInfo
     */
    public BeaconInfo[] getList() {
        return singleton.beacons.values().toArray(new BeaconInfo[0]);
    }
    public Map<String, BeaconInfo> getMap() { return new HashMap<>(singleton.beacons); }

    public static double getDistance(String bName){ return singleton.beacons.get(bName).range; }

    public void clearList() {
        singleton.beacons.clear();
    }

    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        switch (event) {
            case DEVICE_DISCOVERED:
                singleton.beacons.remove(beacon.id);
                singleton.beacons.put(beacon.id, beacon);
                break;
            case DEVICES_UPDATE:
                singleton.beacons.remove(beacon.id);
                singleton.beacons.put(beacon.id, beacon);
                break;
            case DEVICE_LOST:
                singleton.beacons.remove(beacon.id);
                break;
        }
    }
}
