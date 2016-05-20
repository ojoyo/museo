package org.beaconmuseum.beaconmuseum.locator;

import android.util.Pair;

import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skazy on 20.05.16.
 */
public class IndoorLocator {

    private static IndoorLocator instance;
    private static boolean initialized = false;
    private Point myPosition = new Point();

    private Map<String, Point> beaconPositions = new HashMap<>();
    private List<Point> verticles = new ArrayList<>();

    public static void init() {
        if (!initialized) {
            instance = new IndoorLocator();
            initialized = true;
        }
    }

    public boolean calibrate() {
        if (!initialized)
            return false;

//        Calibrator.walkTheRoom();
//
//        beaconPositions = Calibrator.deliverBeaconPositions();
//        verticles = Calibrator.deliverVerticles();

        return true;
    }

    public Point getBeaconPosition(String id) {
        return beaconPositions.get(id);
    }

    public Point getBeaconPosition(BeaconInfo bi) {
        return getBeaconPosition(bi.id);
    }

    public Point[] getVerticles() {
        return verticles.toArray(new Point[verticles.size()]);
    }

    public Point getPosition() {
//        myPosition = Calibrator.definePosition();
        return myPosition;
    }
}
