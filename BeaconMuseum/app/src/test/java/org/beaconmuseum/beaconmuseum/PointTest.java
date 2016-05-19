package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testPoint() throws Exception {
        Point p = new Point(1.5f, -9.99f);

        assertEquals(1.5, p.x, 0.1);
        assertEquals(-9.99, p.y, 0.01);
    }

    @Test
    public void testEquals() throws Exception {
        Point p = new Point(1.0f, 9.0f);
        Point p2 = new Point(1.0f, 9.0f);

        assertFalse(p.equals(new Object()));
        assertFalse(p.equals(null));
        assertTrue(p.equals(p));
        assertTrue(p.equals(p2));
    }
}