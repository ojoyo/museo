package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.discovery.EventType;

import org.beaconmuseum.beaconmuseum.beacons.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by johnny on 13.06.16.
 */
public class AppManagerTest {

    private AppManager appManager;
    private BeaconsInRangeList beaconsList;
    private BeaconInfo beacon1;
    private BeaconInfo beacon2;
    private BeaconInfo beacon3;

    @Before
    public void setUp() throws Exception {
        appManager = new AppManager();
        beaconsList = new BeaconsInRangeList();

        beacon1 = new BeaconInfo("id1", "beacon 1", 20.0);
        beacon2 = new BeaconInfo("id2", "beacon 2", 50.0);
        beacon3 = new BeaconInfo("id3", "beacon 3", 99.0);

        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon3);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon1);
        beaconsList.processBeaconEvent(EventType.DEVICE_DISCOVERED, beacon2);
    }

    @After
    public void tearDown() throws Exception {
        beaconsList.clearList();
    }


    @Test
    public void testRefreshGUI() throws Exception {
        BeaconInfo list[] = appManager.refreshGUI();
        assertEquals(list[0], beacon1);
        assertEquals(list[1], beacon2);
        assertEquals(list[2], beacon3);
    }
}
