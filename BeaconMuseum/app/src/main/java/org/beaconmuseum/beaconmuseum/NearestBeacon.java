package org.beaconmuseum.beaconmuseum;

import java.util.Optional;
/**
 * Trzyma najbliższy beacon w zasięgu
 */

public class NearestBeacon {
    /**
     * Zwraca optional z referencją do najbliższego
     * beacona znajdującego się w zasięgu. Żeby sprawdzić,
     * czy zwrócona wartość jest niepusta, należy
     * skorzystać z metody isPresent()
     */
    public static Optional<BeaconInfo> getInfo() {
        if (BeaconsInRangeList.getInstance().getList().length == 0) {
            return Optional<BeaconInfo>.empty();
        } else {
            BeaconInfo result = BeaconsInRangeList.getInstance().getList()[0];
            for (BeaconInfo i : BeaconsInRangeList.getInstance().getList()) {
                if (i.range < result.range)
                    result = i;
            }
            return Optional<BeaconInfo>.of(result);
        }
    }
}