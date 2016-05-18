package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Color;
import android.graphics.Paint;

public class CanvasStyles {
    Paint getLineStyle() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);

        return p;
    }

    Paint getObserverStyle() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.RED);

        return p;
    }

    Paint getPoiStyle() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);

        return p;
    }
}
