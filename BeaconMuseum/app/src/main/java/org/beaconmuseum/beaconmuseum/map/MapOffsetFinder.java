package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Matrix;

public class MapOffsetFinder {
    private Point[] pts;
    private Matrix matrix;

    public MapOffsetFinder(Point[] pts, Matrix matrix) {
        this.pts = pts.clone();
        this.matrix = matrix;
    }

    private float checkAxis(float begin, int axis) {
        float[] arr = new float[]{
                pts[0].x,
                pts[0].y,
        };

        matrix.mapPoints(arr);
        float offset = arr[axis];

        for (Point p : pts) {
            arr = new float[]{
                    p.x,
                    p.y,
            };

            matrix.mapPoints(arr);

            if (offset > arr[axis])
                offset = arr[axis];
        }

        return begin - offset;
    }

    public float getOffsetX(float begin) {
        return checkAxis(begin, 0);
    }

    public float getOffsetY(float begin) {
        return checkAxis(begin, 1);
    }
}
