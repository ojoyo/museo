package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.Point;
import org.beaconmuseum.beaconmuseum.map.PointToArrayConverter;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointToArrayConverterTest {
    @Test
    public void testConvertPointsToFloatArray() throws Exception {
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
        };

        float[] result = new float[]{
                1, 2, 3, 4,
                3, 4, 1, 2,
        };

        PointToArrayConverter conv = new PointToArrayConverter();
        assertArrayEquals(result, conv.convertPointsToFloatArray(points), 0.1f);
    }
}