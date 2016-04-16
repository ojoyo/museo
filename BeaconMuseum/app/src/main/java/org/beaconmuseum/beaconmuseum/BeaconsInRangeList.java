package org.beaconmuseum.beaconmuseum;

import java.util.ArrayList;
import java.util.HashSet;

public class BeaconsInRangeList {
    private static BeaconsInRangeList ourInstance = new BeaconsInRangeList();
    private HashSet<BeaconInfo> beacons;

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

    public BeaconInfo[] getList() {
        return beacons.toArray(new BeaconInfo[0]);
    }

    public void clearList() {
        beacons.clear();
    }
}
