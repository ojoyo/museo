package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Matrix;

public class ScaleComputator {
    private Point[] pts;

    public ScaleComputator(Point[] pts) {
        this.pts = pts.clone();
    }

    private float getDifference(Matrix matrix, int axis) {
        float[] difference = new float[]{pts[0].x, pts[0].y};
        matrix.mapPoints(difference);
        difference[1 - axis] = difference[axis];

        for (int i = 1; i < pts.length; ++i) {
            float[] p = new float[]{pts[i].x, pts[i].y};
            matrix.mapPoints(p);

            if (p[axis] < difference[0])
                difference[0] = p[axis];

            if (p[axis] > difference[1])
                difference[1] = p[axis];
        }

        return difference[1] - difference[0];
    }

    public float makeBestScale(Matrix m, int width, int height) {
        float differenceX = getDifference(m, 0);
        float differenceY = getDifference(m, 1);

        float scaleX = width / differenceX;
        float scaleY = height / differenceY;

        if (scaleX < scaleY)
            return scaleX;
        else
            return scaleY;
    }
}
