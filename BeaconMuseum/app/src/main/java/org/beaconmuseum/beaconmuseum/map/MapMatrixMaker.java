package org.beaconmuseum.beaconmuseum.map;

import android.graphics.Matrix;

public class MapMatrixMaker {
    public final int topBarMargin = 50; // Wysokość górnego paska

    public void makeMatrix(Matrix matrix, Point[] pts, int margin, int width, int height) {
        new BestFitRotationFinder(pts).makeBestFit(matrix,
                height - margin * 2 - topBarMargin,
                width - margin * 2);

        float scale = new ScaleComputator(pts).makeBestScale(matrix,
                height - margin * 2 - topBarMargin,
                width - margin * 2);
        matrix.postScale(scale, scale);

        // Translacja punktów do właściwego miejsca
        MapOffsetFinder offset = new MapOffsetFinder(pts, matrix);
        matrix.postTranslate(offset.getOffsetX(margin), 0);
        matrix.postTranslate(0, offset.getOffsetY(margin + topBarMargin));
    }
}
