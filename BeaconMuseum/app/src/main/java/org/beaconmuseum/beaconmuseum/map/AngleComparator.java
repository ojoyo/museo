package org.beaconmuseum.beaconmuseum.map;

import java.util.Comparator;

public class AngleComparator implements Comparator<Point> {
    private CrossProduct product;

    public AngleComparator(Point point) {
        product = new CrossProduct(point.x, point.y);
    }

    @Override
    public int compare(Point p1, Point p2) {
        double result = product.product(p1.x, p1.y, p2.x, p2.y);
        double epsilon = 0.001;

        if (-epsilon <= result && result <= epsilon)
            return 0; // Punkty na tej samej prostej
        else if (result < 0)
            return -1; // p1 jest na lewo od p2
        else
            return 1; // p1 jest na prawo od p2
    }
}
