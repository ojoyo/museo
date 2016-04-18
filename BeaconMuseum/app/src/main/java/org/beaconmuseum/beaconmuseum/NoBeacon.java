package org.beaconmuseum.beaconmuseum;

/**
 * represents a non-existent beacon
 */
public class NoBeacon extends BeaconInfo {
    NoBeacon() {
        super(null, null, 0.0);
    }
    @Override
    public boolean isABeacon() {
        return false;
    }
}