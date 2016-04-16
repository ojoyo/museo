package org.beaconmuseum.beaconmuseum;

public class BeaconInfo {
    public final String id;
    public final String name;
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
