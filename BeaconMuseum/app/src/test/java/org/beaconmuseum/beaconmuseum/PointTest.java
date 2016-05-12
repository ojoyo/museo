package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testPoint() throws Exception {
        Point p = new Point(1.5, -9.99);

        assertEquals(1.5, p.x, 0.1);
        assertEquals(-9.99, p.y, 0.01);
    }
}