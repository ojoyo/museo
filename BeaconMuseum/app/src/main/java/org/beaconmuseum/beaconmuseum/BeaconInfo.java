package org.beaconmuseum.beaconmuseum;

/**
 * Obiekt reprezentujący Beacon.
 */
public class BeaconInfo {
    /** ID beacona */
    public final String id;
    /** Nazwa beacona */
    public final String name;
    /** Odległość beacona od odbiornika */
    public final double range;

    BeaconInfo(String id, String name, double range) {
        this.id = id;
        this.name = name;
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!this.getClass().equals(o.getClass()))
            return false;

        BeaconInfo beacon = (BeaconInfo) o;
        return beacon.id.equals(this.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
