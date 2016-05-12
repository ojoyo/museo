package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.AngleComparator;
import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class AngleComparatorTest {
    @Test
    public void testCompare() throws Exception {
        AngleComparator comp = new AngleComparator(new Point(0.0, 0.0));

        Point p1 = new Point(1.0, 0.0);
        Point p2 = new Point(0.0, 1.0);
        Point p3 = new Point(-1.0, 0.0);

        assertEquals(1, comp.compare(p1, p2));
        assertEquals(-1, comp.compare(p2, p1));
        assertEquals(0, comp.compare(p1, p3));
        assertEquals(0, comp.compare(p3, p1));
    }
}