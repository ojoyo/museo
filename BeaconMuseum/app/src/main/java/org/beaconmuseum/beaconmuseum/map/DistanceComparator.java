package org.beaconmuseum.beaconmuseum.map;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Point> {
    private Point start;

    public DistanceComparator(Point point) {
        start = point;
    }

    @Override
    public int compare(Point p1, Point p2) {
        double d1x = p1.x - start.x;
        double d1y = p1.y - start.y;
        double d1 = d1x*d1x + d1y*d1y;

        double d2x = p2.x - start.x;
        double d2y = p2.y - start.y;
        double d2 = d2x*d2x + d2y*d2y;

        if (d1 == d2)
            return 0; // Punkty tak samo odległe
        else if (d1 < d2)
            return -1; // p1 jest bliżej od p2 względem startu
        else
            return 1; // p1 jest dalej od p2 względem startu
    }
}
