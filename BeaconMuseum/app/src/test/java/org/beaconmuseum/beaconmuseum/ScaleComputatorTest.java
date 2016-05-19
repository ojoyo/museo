package org.beaconmuseum.beaconmuseum;

import android.graphics.Matrix;

import org.beaconmuseum.beaconmuseum.map.Point;
import org.beaconmuseum.beaconmuseum.map.ScaleComputator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ScaleComputatorTest {
    @Test
    public void testMakeBestScale() throws Exception {
        Point[] pts = new Point[]{
                new Point(0, 0),
                new Point(100, 50),
                new Point(0, -150),
        };

        ScaleComputator s = new ScaleComputator(pts);
        Matrix m = new Matrix();

        assertEquals(1, s.makeBestScale(m, 400, 200), 0.1);
        assertEquals(2, s.makeBestScale(m, 200, 400), 0.1);
        assertEquals(1.5, s.makeBestScale(m, 1000, 300), 0.1);
    }
}