package org.beaconmuseum.beaconmuseum;

import android.graphics.Matrix;

import org.beaconmuseum.beaconmuseum.map.MapOffsetFinder;
import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MapOffsetFinderTest {
    Point[] pts;
    MapOffsetFinder finder;
    Matrix m;

    @Before
    public void setUp() throws Exception {
        pts = new Point[]{
                new Point(-100, 2),
                new Point(1000, 1000),
                new Point(-200, 1),
        };

        m = new Matrix();
        finder = new MapOffsetFinder(pts, m);
    }

    @Test
    public void testGetOffsetX() throws Exception {
        assertEquals(201, finder.getOffsetX(1), 0.5);
        assertEquals(-50, finder.getOffsetX(-250), 0.5);
    }

    @Test
    public void testGetOffsetY() throws Exception {
        assertEquals(0, finder.getOffsetY(1), 0.1);
        assertEquals(1, finder.getOffsetY(2), 0.1);
        assertEquals(-251, finder.getOffsetY(-250), 0.1);
    }
}