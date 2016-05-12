package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Point;

public class CrossProduct {
    private double x0, y0;

    public CrossProduct(double x, double y) {
        x0 = x;
        y0 = y;
    }

    public CrossProduct() {
        this(0.0, 0.0);
    }

    public double product(double ax, double ay, double bx, double by) {
        ax -= x0;
        ay -= y0;
        bx -= x0;
        by -= y0;

        return ax * by - ay * bx;
    }
}
