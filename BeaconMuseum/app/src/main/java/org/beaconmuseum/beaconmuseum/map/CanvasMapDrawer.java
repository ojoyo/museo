package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class CanvasMapDrawer implements MapDrawerInterface {
    Canvas surface;
    Point observer;
    Point[] pois, room;

    public CanvasMapDrawer(Canvas canvas) {
        surface = canvas;
    }

    @Override
    public void setRoomBorders(Point[] pts) {
        this.room = pts;
    }

    @Override
    public void setPOIs(Point[] pts) {
        this.pois = pts;
    }

    @Override
    public void setPosition(Point observer) {
        this.observer = observer;
    }

    @Override
    public void draw() {
        int margin = 30; // Margines w pikselach
        int topBarMargin = 50; // Wysokość górnego paska
        Point[] pts = getPoints();
        Matrix matrix = new Matrix();

        new BestFitRotationFinder(pts).makeBestFit(matrix,
                        surface.getHeight() - margin * 2 - topBarMargin,
                        surface.getWidth() - margin * 2);

        float scale = new ScaleComputator(pts).makeBestScale(matrix,
                surface.getHeight() - margin * 2 - topBarMargin,
                surface.getWidth() - margin * 2);
        matrix.postScale(scale, scale);

        // Translacja punktów do właściwego miejsca
        MapOffsetFinder offset = new MapOffsetFinder(pts, matrix);
        matrix.postTranslate(offset.getOffsetX(margin), 0);
        matrix.postTranslate(0, offset.getOffsetY(margin + topBarMargin));

        surface.setMatrix(matrix);
        fastRedraw(observer);
    }

    private Point[] getPoints() {
        ArrayList<Point> arr = new ArrayList<>();

        arr.addAll(Arrays.asList(pois));
        arr.addAll(Arrays.asList(room));
        arr.add(observer);

        return arr.toArray(new Point[0]);
    }

    private float[] convertPointsToFloatArray(Point[] pts) {
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

    @Override
    public void fastRedraw(Point observer) {
        CanvasStyles styles = new CanvasStyles();

        // Rysowanie granicy pomieszczenia
        surface.drawLines(convertPointsToFloatArray(room), styles.getLineStyle());

        // Rysowanie POI
        for (Point p : pois) {
            int radius = 5;
            surface.drawCircle(p.x, p.y, radius, styles.getPoiStyle());
        }

        // Rysowanie obserwatora
        int observerRadius = 5;
        surface.drawCircle(observer.x, observer.y, observerRadius, styles.getObserverStyle());
    }
}
