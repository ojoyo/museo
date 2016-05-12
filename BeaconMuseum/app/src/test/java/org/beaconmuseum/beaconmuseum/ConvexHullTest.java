package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.map.ConvexHull;
import org.beaconmuseum.beaconmuseum.map.Point;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvexHullTest {
    @Test
    public void testGetConvexHull() throws Exception {
        Point[] points, expected;

        points = new Point[]{
                new Point(3, 3),
                new Point(100, 1),
                new Point(0, 1),
                new Point(2, 2),
                new Point(100, 0),
                new Point(1, -1),
                new Point(0, 0),
                new Point(1, 1),
        };

        expected = new Point[]{
                new Point(0, 0),
                new Point(0, 1),
                //new Point(1, 1),
                //new Point(2, 2),
                new Point(3, 3),
                new Point(100, 1),
                new Point(100, 0),
                new Point(1, -1),
        };

        ConvexHull convexHull = new ConvexHull();
        Point[] newPoints = convexHull.getConvexHull(points);

        assertArrayEquals(expected, newPoints);
    }

    @Test
    public void testTriangleConvexHull() throws Exception {
        Point[] points, expected;

        points = new Point[]{
                new Point(0, 0),
                new Point(0, 1),
                new Point(0, 2),
                new Point(0, 3),
                new Point(1, 1),
        };

        expected = new Point[]{
                new Point(0, 0),
                new Point(0, 3),
                new Point(1, 1),
        };

        ConvexHull convexHull = new ConvexHull();
        Point[] newPoints = convexHull.getConvexHull(points);

        assertArrayEquals(expected, newPoints);
    }

    @Test
    public void testStanczykConvexHull() throws Exception {
        Point[] points, expected;

        points = new Point[]{
                new Point(-6, -2),
                new Point(-1, -1),
                new Point(-3, 4),
                new Point(-7, 5),
                new Point(5, -2),
                new Point(3, 2),
                new Point(7, 4),
                new Point(8, 7),
        };

        expected = new Point[]{
                new Point(-7, 5),
                new Point(8, 7),
                new Point(5, -2),
                new Point(-6, -2),
        };

        ConvexHull convexHull = new ConvexHull();
        points = convexHull.getConvexHull(points);

        assertArrayEquals(expected, points);
    }
}