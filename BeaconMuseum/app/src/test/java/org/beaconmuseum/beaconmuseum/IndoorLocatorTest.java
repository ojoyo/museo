package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.beaconmuseum.beaconmuseum.locator.IndoorLocator;
import org.junit.Before;
import org.junit.Test;

import static android.test.MoreAsserts.assertEquals;

/**
 * Created by skazy on 14.06.16.
 */
public class IndoorLocatorTest {
    @Test
    public void testUninitialized() {
        IndoorLocator.calibrate();
    }

    @Test
    public void basicTest() {
        IndoorLocator.init(null);
    }

    @Test
    public void getBeaconsTest() {
        IndoorLocator.init(null);
        IndoorLocator.getBeaconPosition("test");
        IndoorLocator.getBeaconPosition(new BeaconInfo("id", "name", 0f));
    }

    @Test
    public void getPositionTest() {
        IndoorLocator.init(null);
        IndoorLocator.getPosition();
        //IndoorLocator.getVerticles();
    }

    @Test
    public void fakeWalk() {
        IndoorLocator.init(null);
        IndoorLocator.calibrate();
    }
}

