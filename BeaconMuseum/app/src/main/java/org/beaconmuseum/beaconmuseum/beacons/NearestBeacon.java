package org.beaconmuseum.beaconmuseum.beacons;

import com.google.inject.Inject;

/**
 * Trzyma najbliższy beacon w zasięgu
 */

public class NearestBeacon {
    @Inject private BeaconsInRangeList beaconsInRangeList;

    /**
     * Zwraca informację o beaconie znajdującym się najbliżej
     * odbiornika. Jeśli nie ma beacona w zasięgu, zwraca NoBeacon
     * Aby testować istnienie, należy wołać metodę isABeacon()
     */
    public BeaconInfo getInfo() {
        BeaconInfo[] list = beaconsInRangeList.getList();

        if (list.length == 0)
            return new NoBeacon();

        BeaconInfo result = list[0];

        for (BeaconInfo i : list)
            if (i.range < result.range)
                result = i;

        return result;
    }
}