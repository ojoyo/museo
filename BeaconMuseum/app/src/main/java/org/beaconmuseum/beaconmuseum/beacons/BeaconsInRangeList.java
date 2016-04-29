package org.beaconmuseum.beaconmuseum.beacons;

import com.google.inject.Singleton;
import com.kontakt.sdk.android.ble.discovery.EventType;
import java.util.HashSet;

/**
 * Trzyma listę beaconów będących w zasięgu.
 */
@Singleton
public class BeaconsInRangeList implements BeaconEventProcessorInterface {
    private HashSet<BeaconInfo> beacons = new HashSet<>();

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
        return beacons.toArray(new BeaconInfo[0]);
    }

    public void clearList() {
        beacons.clear();
    }

    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        switch (event) {
            case DEVICE_DISCOVERED:
                beacons.remove(beacon);
                beacons.add(beacon);
                break;
            case DEVICES_UPDATE:
                beacons.remove(beacon);
                beacons.add(beacon);
                break;
            case DEVICE_LOST:
                beacons.remove(beacon);
                break;
        }
    }
}
