package org.beaconmuseum.beaconmuseum.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ConvexHull {
    private Point getLeftPoint(Point[] points) {
        Point res = points[0];

        for (Point p : points)
            if (p.x < res.x)
                res = p;
            else if (p.x == res.x && p.y < res.y)
                res = p;

        return res;
    }

    private Point[] sortPoints(Point[] points, Point left) {
        final AngleComparator angleComp = new AngleComparator(left);
        final DistanceComparator distComp = new DistanceComparator(left);

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                int res = angleComp.compare(lhs, rhs);

                if (res == 0)
                    res = distComp.compare(lhs, rhs);

                if (res == 0)
                    res = Integer.compare(lhs.hashCode(), rhs.hashCode());

                return res;
            }
        });

        return points;
    }

    private Point[] filterPoints(Point[] points) {
        ArrayList<Point> list = new ArrayList<>();
        list.add(points[0]);
        list.add(points[1]);
        list.add(points[2]);

        for (int i = list.size(); i < points.length; ++i) {
            AngleComparator comp = new AngleComparator(list.get(list.size() - 2));

            while (list.size() > 2 && comp.compare(list.get(list.size() - 1), points[i]) != -1) {
                list.remove(list.size() - 1);
                comp = new AngleComparator(list.get(list.size() - 2));
            }

            if (comp.compare(list.get(list.size() - 1), points[i]) != -1)
                list.remove(list.size() - 1);

            list.add(points[i]);
        }

        return list.toArray(new Point[0]);
    }

    public Point[] getConvexHull(Point[] points) {
        Point left = getLeftPoint(points);
        points = sortPoints(points, left);
        points = filterPoints(points);

        return points;
    }
}
