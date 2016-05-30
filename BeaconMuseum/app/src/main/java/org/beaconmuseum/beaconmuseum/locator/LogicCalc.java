package org.beaconmuseum.beaconmuseum.locator;

/**
 * Created by skazy on 20.05.16.
 */
public class LogicCalc {

    /**
     * Sets point coordinates for all three beacons as d3 is (0,0) and d2 is (x, 0)
     */
    public static Point[] makeTriangle(double a_b, double a_c, double c_b, double c_a) {

        Point a, b, c;

        b = new Point(0,0);
        c = new Point(c_b, 0);

        double aX = (a_b * a_b - a_c * a_c - c_b * c_b ) / (-2 * c_b);
        double aY = Math.sqrt(a_b * a_b - aX * aX);

        a = new Point(aX, aY);

        Point toReturn[] = new Point[3];
        toReturn[0] = a;
        toReturn[1] = b;
        toReturn[2] = c;

        return toReturn;
    }

    public static Point getPosition(Point[] triangle, Double[] distances) {
        return null;
    }

    private static double twoPointDistance(Point p1, Point p2) {
        return Math.sqrt( Math.pow(p1._X - p2._X, 2) +  Math.pow(p1._Y - p2._Y, 2) );
    }


}
