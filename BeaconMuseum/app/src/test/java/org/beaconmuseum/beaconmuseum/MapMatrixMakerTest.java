package org.beaconmuseum.beaconmuseum;

import android.graphics.Matrix;

import org.beaconmuseum.beaconmuseum.map.MapMatrixMaker;
import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class MapMatrixMakerTest {
    Matrix matrix;
    MapMatrixMaker maker;

    @Before
    public void setUp() throws Exception {
        matrix = new Matrix();
        maker = new MapMatrixMaker();
    }

    @Test
    public void testMakeMatrix() throws Exception {
        int margin = 0;
        int width = 200;
        int height = 150;

        Point[] points = new Point[]{
                new Point(0, 0),
                new Point(0, 100),
                new Point(200, 100),
                new Point(200, 0),
        };

        maker.makeMatrix(matrix, points, margin, width, height);

        // Punkt 1
        float[] pts = new float[]{0, 0};
        matrix.mapPoints(pts);
        assertEquals(0, pts[0], 0.5);
        assertEquals(0, pts[1], 0.5);

        // Punkt 2
        pts = new float[]{200, 100};
        matrix.mapPoints(pts);
        assertEquals(200, pts[0], 0.5);
        assertEquals(100, pts[1], 0.5);
    }
}