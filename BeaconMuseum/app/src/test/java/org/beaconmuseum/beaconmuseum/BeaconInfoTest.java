package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.common.profile.RemoteBluetoothDevice;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BeaconInfoTest {
    BeaconInfo beaconA;
    BeaconInfo beaconB1;
    BeaconInfo beaconB2;

    @Before
    public void setUp() throws Exception {
        beaconA = new BeaconInfo("A", "A Beacon", 10.0);
        beaconB1 = new BeaconInfo("B", "B1 Beacon", 10.0);
        beaconB2 = new BeaconInfo("B", "B2 Beacon", 100.0);
    }

    @Test
    public void testFields() throws Exception {
        assertEquals(beaconA.id, "A");
        assertEquals(beaconA.name, "A Beacon");
        assertEquals(beaconA.range, 10.0, 0.5);
    }

    @Test
    public void testOptionalConstructor() throws Exception {
        RemoteBluetoothDevice device = mock(RemoteBluetoothDevice.class);

        when(device.getUniqueId()).thenReturn("A");
        when(device.getName()).thenReturn("A Beacon");
        when(device.getDistance()).thenReturn(10.0);

        BeaconInfo beacon = new BeaconInfo(device);

        assertEquals(beacon.id, "A");
        assertEquals(beacon.name, "A Beacon");
        assertEquals(beacon.range, 10.0, 0.5);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(beaconB1, beaconB2);
        assertEquals(beaconB1, beaconB1);
        assertNotEquals(beaconA, beaconB1);
        assertNotEquals(beaconA, beaconB2);
        assertNotEquals(beaconA, null);
        assertNotEquals(beaconA, "AAAAAA");
    }

    @Test
    public void testIsABeacon() throws Exception {
        assertTrue(beaconA.isABeacon());
        assertTrue(beaconB1.isABeacon());
    }

    @Test
    public void testHash() throws Exception {
        assertNotNull(beaconA.hashCode());
    }
}