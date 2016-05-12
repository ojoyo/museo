package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.DistanceComparator;
import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistanceComparatorTest {

    @Test
    public void testCompare() throws Exception {
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(0.0, 1.0);
        Point p3 = new Point(-1.0, 0.0);
        Point p4 = new Point(-100.0, 0.0);

        DistanceComparator comp = new DistanceComparator(p1);

        assertEquals(0, comp.compare(p2, p3));
        assertEquals(0, comp.compare(p3, p2));
        assertEquals(-1, comp.compare(p2, p4));
        assertEquals(1, comp.compare(p4, p2));
    }
}