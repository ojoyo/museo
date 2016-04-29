package org.beaconmuseum.beaconmuseum;

import com.google.inject.AbstractModule;
import org.beaconmuseum.beaconmuseum.beacons.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import roboguice.RoboGuice;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class NearestBeaconTest {
    NearestBeacon info;
    BeaconsInRangeList beaconsInRangeListMock;
    BeaconInfo beacon1;
    BeaconInfo beacon2;

    @Before
    public void setUp() throws Exception {
        beaconsInRangeListMock = mock(BeaconsInRangeList.class);
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new MyTestModule());
        info = RoboGuice.getInjector(RuntimeEnvironment.application).getInstance(NearestBeacon.class);

        beacon1 = new BeaconInfo("A", "a", 1.0);
        beacon2 = new BeaconInfo("B", "b", 1.001);
    }

    @After
    public void tearDown() throws Exception {
        RoboGuice.Util.reset();
    }

    @Test
    public void testNoBeacon() throws Exception {
        when(beaconsInRangeListMock.getList()).thenReturn(new BeaconInfo[0]);

        BeaconInfo beacon = info.getInfo();

        assertNotNull(beacon);
        assertFalse(beacon.isABeacon());
        assertTrue(beacon instanceof NoBeacon);
    }

    @Test
    public void testOneBeacon() throws Exception {
        when(beaconsInRangeListMock.getList()).thenReturn(new BeaconInfo[]{beacon1});

        BeaconInfo beacon = info.getInfo();

        assertNotNull(beacon);
        assertEquals(beacon, beacon1);
    }

    @Test
    public void testTwoBeacons() throws Exception {
        when(beaconsInRangeListMock.getList())
                .thenReturn(new BeaconInfo[]{beacon2, beacon1});

        BeaconInfo beacon = info.getInfo();

        assertNotNull(beacon);
        assertEquals(beacon, beacon1);
    }

    @Test
    public void testChangingList() throws Exception {
        when(beaconsInRangeListMock.getList())
                .thenReturn(new BeaconInfo[]{beacon1}, new BeaconInfo[0]);

        BeaconInfo beacon = info.getInfo();

        assertNotNull(beacon);
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(BeaconsInRangeList.class).toInstance(beaconsInRangeListMock);
        }
    }
}