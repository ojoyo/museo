package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.EventType;

import org.beaconmuseum.beaconmuseum.beacons.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeaconsInRangeListTest {
    private BeaconsInRangeList beaconsList;
    private BeaconInfo beacon1;
    private BeaconInfo beacon2;
    private BeaconInfo beacon3A;
    private BeaconInfo beacon3B;

    @Before
    public void setUp() throws Exception {
        beaconsList = new BeaconsInRangeList();

        beacon1 = new BeaconInfo("id1", "beacon 1", 20.0);
        beacon2 = new BeaconInfo("id2", "beacon 2", 50.0);
        beacon3A = new BeaconInfo("id3", "beacon 3A", 99.0);
        beacon3B = new BeaconInfo("id3", "beacon 3B", 1.0);
    }

    @After
    public void tearDown() throws Exception {
        beaconsList.clearList();
    }

    @Test
    public void testGetList() throws Exception {
        beaconsList.clearList();

        BeaconInfo[] list = beaconsList.getList();
        assertNotNull(list);
        assertEquals(0, list.length);
    }

    @Test
    public void testClearList() throws Exception {
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon1);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon2);

        beaconsList.clearList();

        BeaconInfo[] list = beaconsList.getList();
        assertNotNull(list);
        assertEquals(0, list.length);
    }

    @Test
    public void testProcessBeaconEventAdd() throws Exception {
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon1);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon2);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon3A);

        BeaconInfo[] list = beaconsList.getList();
        assertNotNull(list);
        assertEquals(3, list.length);
    }

    @Test
    public void testProcessBeaconEventChange() throws Exception {
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon3A);
        beaconsList.processBeaconEvent(EventType.DEVICES_UPDATE, beacon3B);

        BeaconInfo[] list = beaconsList.getList();
        assertNotNull(list);
        assertEquals(1, list.length);

        BeaconInfo element = list[0];
        assertEquals("Name doesn't match!", "beacon 3B", element.name);
        assertEquals("ID doesn't match!", "id3", element.id);
        assertEquals("Invalid range!", 1.0, element.range, 0.5);
    }

    @Test
    public void testProcessBeaconEventDelete() throws Exception {
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon1);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon2);
        beaconsList.processBeaconEvent(EventType.DEVICE_LOST, beacon2);

        BeaconInfo[] list = beaconsList.getList();
        assertNotNull(list);
        assertEquals(1, list.length);

        BeaconInfo beacon = list[0];
        assertEquals(beacon1, beacon);
    }
}