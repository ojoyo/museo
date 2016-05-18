package org.beaconmuseum.beaconmuseum.map;

public class Point {
    public final float x, y;

    public Point(float x, float y) {
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
