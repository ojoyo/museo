package org.beaconmuseum.beaconmuseum;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Trzyma listę beaconów będących w zasięgu.
 */
public class BeaconsInRangeList {
    private static BeaconsInRangeList ourInstance = new BeaconsInRangeList();
    private HashSet<BeaconInfo> beacons = new HashSet<>();

    /**
     * Daje dostęp do instancji singletona.
     *
     * @return obiekt singletona BeaconsInRangeList.
     */
    public static BeaconsInRangeList getInstance() {
        return ourInstance;
    }

    private BeaconsInRangeList() {}

    public void addBeaconToList(BeaconInfo beacon) {
        beacons.add(beacon);
    }

    public void removeBeaconFromList(BeaconInfo beacon) {
        beacons.remove(beacon);
    }

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
}
