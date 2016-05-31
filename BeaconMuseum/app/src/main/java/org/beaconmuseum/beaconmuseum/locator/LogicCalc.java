package org.beaconmuseum.beaconmuseum.locator;

import android.util.Pair;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by skazy on 20.05.16.
 */
public class LogicCalc {


    /**
     * Sets point coordinates for all three beacons as d3 is (0,0) and d2 is (x, 0)
     */
    public static Point[] makeTriangle(double a_b, double a_c, double c_b, double c_a) {

        Point b = new Point(0, 0);
        Point c = new Point(c_b, 0);

        double aX = (a_b * a_b - a_c * a_c - c_b * c_b) / (-2 * c_b);
        double aY = Math.sqrt(a_b * a_b - aX * aX);

        Point a = new Point(aX, aY);

        Point toReturn[] = new Point[3];
        toReturn[0] = a;
        toReturn[1] = b;
        toReturn[2] = c;

        return toReturn;
    }

    public static Point getPosition(Point[] triangle, Double[] distances) {

        Point A = triangle[0];
        Point B = triangle[1];
        Point C = triangle[2];

        double Pa = distances[0];
        double Pb = distances[1];
        double Pc = distances[2];

        //liczymy pole PBC z herona i wysokosc z P na BC
        double h1 = heronHeight(Pb, Pc, twoPointDistance(B, C));

        //teraz PAB
        double h2 = heronHeight(Pa, Pb, twoPointDistance(A, B));

        // OK, czyli wyznaczmy prostą przech. przez B, C i przesuniemy ją o h1 w górę/dół, podobnie
        //tę drugą, i wyznaczymy 4pkt przecięcia i wybierzemy najbliższy

        //Prosta BC ma wzór U1 x + V1 y + Z1 = 0, AB jw. ale U2, V2, Z2
        double U1 = (C._Y - B._Y);
        double V1 = (B._X - C._X);
        double Z1 = (C._X * (B._Y - C._Y) + C._Y * (C._X - B._X));
        double h11 = h1 * (Math.sqrt(U1 * U1 + V1 * V1)) + Z1;
        double h12 = -(h11 - Z1) + Z1;



        double U2 = (A._Y - B._Y);
        double V2 = (B._X - A._X);
        double Z2 = (A._X * (B._Y - A._Y) + A._Y * (A._X - B._X));
        double h21 = h2 * (Math.sqrt(U2 * U2 + V2 * V2)) + Z2;
        double h22 = -(h21 - Z2) + Z2;
        //dotąd git!

        Point P1 = solve2lines(U1, V1, h11, U2, V2, h21);
        Point P2 = solve2lines(U1, V1, h12, U2, V2, h21);
        Point P3 = solve2lines(U1, V1, h11, U2, V2, h22);
        Point P4 = solve2lines(U1, V1, h12, U2, V2, h22);

        double Psum = (Pa + Pb + Pc);

        ArrayList< Pair<Point, Double> > candidates = new ArrayList<>();
        candidates.add(Pair.create(P1, getSumOfDistances(P1, A, B, C) - Psum));
        candidates.add(Pair.create(P2, getSumOfDistances(P2, A, B, C) - Psum));
        candidates.add(Pair.create(P3, getSumOfDistances(P3, A, B, C) - Psum));
        candidates.add(Pair.create(P4, getSumOfDistances(P4, A, B, C) - Psum));

        return bestPoint(candidates);
    }

    private static double heronHeight(double ab, double ac, double bc) // opuszczona z A na BC
    {
        double polObw = (bc + ab + ac) / 2;
        double pole =
                Math.sqrt(polObw * (polObw - bc) * (polObw - ab) * (polObw - ac));

        return (pole / bc) * 2; // wysokosc
    }

    private static double twoPointDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1._X - p2._X, 2) + Math.pow(p1._Y - p2._Y, 2));
    }

    private static Point solve2lines(double a1, double b1, double c1, double a2, double b2, double c2)
    //jedna: a1x+b1y+c1=0, druga podobnie
    {
        double W = a1 * b2 - a2 * b1;

        if(W == 0)//jesli rownolegle
            return null;

        double Wx = -c1 * b2 + c2 * b1;
        double Wy = -c2 * a1 + a2 * c1;

        return new Point(Wx / W, Wy / W);

    }

    private static Point bestPoint(ArrayList< Pair<Point, Double> > candidates)
    // pary: punkt, suma odleglosci
    //robimy min z sum odleglosci
    {
        if(candidates.size() == 0)
            return null;

        Point toReturn = candidates.get(0).first;
        double min = candidates.get(0).second;
        for (Pair<Point, Double> pd:candidates) {
            if(pd.second < min){
                toReturn = pd.first;
                min = pd.second;
            }

        }
        return toReturn;
    }

    private static double getSumOfDistances(Point p, Point a, Point b, Point c)
    {
        return twoPointDistance(p, a) + twoPointDistance(p, b) + twoPointDistance(p, c);
    }



    public static void main(String[] args) {

        Point points[] = {new Point(1.74, 3.64), new Point(0.6, 0.54),
                new Point(4.62, 1.06)};

        Double distances[] = {3.23, 5.25, 2.42};

        Point wyn = getPosition(points, distances);

    }

}
