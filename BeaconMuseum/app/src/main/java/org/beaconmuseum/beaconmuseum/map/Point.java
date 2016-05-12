package org.beaconmuseum.beaconmuseum.map;

public class Point {
    public final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Point))
            return false;

        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }
}
