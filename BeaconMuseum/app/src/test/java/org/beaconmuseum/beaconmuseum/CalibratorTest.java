package org.beaconmuseum.beaconmuseum;

import android.content.Context;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.beaconmuseum.beaconmuseum.beacons.BeaconsInRangeList;
import org.beaconmuseum.beaconmuseum.beacons.NearestBeacon;
import org.beaconmuseum.beaconmuseum.locator.Calibrator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RuntimeEnvironment;

import roboguice.RoboGuice;

import static org.mockito.Mockito.mock;


/**
 * Created by skazy on 14.06.16.
 */


@RunWith(MockitoJUnitRunner.class)
public class CalibratorTest {
    @Mock
    Context mMockContext;

    @Before
    public void setUp() throws Exception {
        BeaconsInRangeList beaconsInRangeListMock = mock(BeaconsInRangeList.class);

        BeaconInfo beacon1 = new BeaconInfo("A", "a", 1.0);
        BeaconInfo beacon2 = new BeaconInfo("B", "b", 1.001);
    }

    @Test
    public void simpleTest() {
        Calibrator calibrator = new Calibrator();
        calibrator.walkTheRoom(mMockContext);
    }

}
