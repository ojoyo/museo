package org.beaconmuseum.beaconmuseum.map;

public class PointToArrayConverter {
    public float[] convertPointsToFloatArray(Point[] pts) {
        float[] ret = new float[pts.length * 4];

        ret[0] = pts[0].x;
        ret[1] = pts[0].y;

        for (int i = 1; i < pts.length; ++i) {
            ret[4 * i - 2] = pts[i].x;
            ret[4 * i - 1] = pts[i].y;
            ret[4 * i] = pts[i].x;
            ret[4 * i + 1] = pts[i].y;
        }

        ret[ret.length - 2] = pts[0].x;
        ret[ret.length - 1] = pts[0].y;

        return ret;
    }
}
