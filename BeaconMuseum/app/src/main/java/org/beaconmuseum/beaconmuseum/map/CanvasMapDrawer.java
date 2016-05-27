package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.ArrayList;
import java.util.Arrays;

public class CanvasMapDrawer implements MapDrawerInterface {
    private final int margin = 30; // Margines w pikselach
    Canvas surface;
    Point observer;
    Point[] pois, room;
    PointToArrayConverter converter = new PointToArrayConverter();
    MapMatrixMaker matrixMaker = new MapMatrixMaker();

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
        Point[] pts = getPoints();
        Matrix matrix = new Matrix();
        matrixMaker.makeMatrix(matrix, pts, margin, surface.getWidth(), surface.getHeight());
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

    @Override
    public void fastRedraw(Point observer) {
        CanvasStyles styles = new CanvasStyles();

        // Rysowanie granicy pomieszczenia
        surface.drawLines(converter.convertPointsToFloatArray(room), styles.getLineStyle());

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
