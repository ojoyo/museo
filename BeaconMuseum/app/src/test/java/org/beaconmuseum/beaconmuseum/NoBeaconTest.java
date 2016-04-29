package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.beacons.NoBeacon;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoBeaconTest {

    @Test
    public void testIsABeacon() throws Exception {
        NoBeacon beacon = new NoBeacon();

        assertFalse(beacon.isABeacon());
    }
}