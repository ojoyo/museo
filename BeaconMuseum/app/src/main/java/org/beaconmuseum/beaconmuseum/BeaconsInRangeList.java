package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.EventType;
import com.kontakt.sdk.android.common.profile.RemoteBluetoothDevice;
import java.util.HashSet;

/**
 * Trzyma listę beaconów będących w zasięgu.
 */
public class BeaconsInRangeList implements BeaconEventProcessorInterface {
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

    public void processBeaconEvent(EventType event, RemoteBluetoothDevice device) {
        BeaconsInRangeList beaconsList = BeaconsInRangeList.getInstance();
        BeaconInfo beacon = new BeaconInfo(
                device.getUniqueId(),
                device.getName(),
                device.getDistance()
        );

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
