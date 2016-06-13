package org.beaconmuseum.beaconmuseum.locator;

import android.content.Context;
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

    private Context context;

    IndoorLocator(Context context) { this.context = context; }

    public static void init(Context context) {
        if (!initialized) {
            instance = new IndoorLocator(context);
            initialized = true;
        }
    }

    public static boolean calibrate() {
        if (!initialized)
            return false;
        Calibrator calibrator = new Calibrator();

        calibrator.walkTheRoom(instance.context); // można przenieść do konstruktora

        instance.beaconPositions = Calibrator.deliverBeaconPositions();
        instance.verticles = Calibrator.deliverVerticles();

        return true;
    }

    public static Point getBeaconPosition(String id) { return instance.beaconPositions.get(id); }

    public static Point getBeaconPosition(BeaconInfo bi) { return getBeaconPosition(bi.id); }

    public static Point[] getVerticles() {
        return instance.verticles.toArray(new Point[instance.verticles.size()]);
    }

    public static Point getPosition() {
//        myPosition = Calibrator.definePosition();
        return instance.myPosition;
    }
}
