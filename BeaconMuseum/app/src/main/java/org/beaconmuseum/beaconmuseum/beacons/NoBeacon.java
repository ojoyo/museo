package org.beaconmuseum.beaconmuseum.beacons;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;

/**
 * represents a non-existent beacon
 */
public class NoBeacon extends BeaconInfo {
    public NoBeacon() {
        super(null, null, 0.0);
    }

    @Override
    public boolean isABeacon() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}