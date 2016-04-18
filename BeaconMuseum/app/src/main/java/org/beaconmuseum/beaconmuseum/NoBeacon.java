package org.beaconmuseum.beaconmuseum;

/**
 * Represents an empty beacon
 */
public class NoBeacon extends BeaconInfo {
    NoBeacon() {
        super("", "", 0.0);
    }

    /**
     * @return false
     */
    @Override
    public boolean isABeacon() {
        return false;
    }
}