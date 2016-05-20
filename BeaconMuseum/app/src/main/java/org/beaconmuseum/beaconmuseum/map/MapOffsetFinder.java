package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Matrix;

public class MapOffsetFinder {
    private Point[] pts;
    private Matrix matrix;

    public MapOffsetFinder(Point[] pts, Matrix matrix) {
        this.pts = pts.clone();
        this.matrix = matrix;
    }

    public float getOffsetX(float begin) {
        float[] arr = new float[]{
                pts[0].x,
                pts[0].y,
        };

        matrix.mapPoints(arr);
        float offset = arr[0];

        for (Point p : pts) {
            arr = new float[]{
                    p.x,
                    p.y,
            };

            matrix.mapPoints(arr);

            if (offset > arr[0])
                offset = arr[0];
        }

        return begin - offset;
    }

    public float getOffsetY(float begin) {
        float[] arr = new float[]{
                pts[0].x,
                pts[0].y,
        };

        matrix.mapPoints(arr);
        float offset = arr[1];

        for (Point p : pts) {
            arr = new float[]{
                    p.x,
                    p.y,
            };

            matrix.mapPoints(arr);

            if (offset > arr[1])
                offset = arr[1];
        }

        return begin - offset;
    }
}
