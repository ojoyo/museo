package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.beaconmuseum.beaconmuseum.beacons.BeaconsInRangeList;
import org.beaconmuseum.beaconmuseum.beacons.NearestBeacon;
import org.beaconmuseum.beaconmuseum.locator.Point;
import org.junit.Before;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import roboguice.RoboGuice;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by skazy on 14.06.16.
 */
public class LocatorPointTest {


    @Test
    public void testEmpty() {
        Point p = new Point();
        Point p2 = new Point(0, 0);

        assertTrue(p._X == p2._X);
        assertTrue(p._Y == p2._Y);

    }


}
