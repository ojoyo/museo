package org.beaconmuseum.beaconmuseum.locator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import org.beaconmuseum.beaconmuseum.AppManager;
import org.beaconmuseum.beaconmuseum.CalibrateActivity;
import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.beaconmuseum.beaconmuseum.beacons.BeaconManager;
import org.beaconmuseum.beaconmuseum.beacons.BeaconsInRangeList;
import org.beaconmuseum.beaconmuseum.dialogs.CalibratorMultiChoose;
import org.beaconmuseum.beaconmuseum.dialogs.CalibratorStandHere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by skazy on 20.05.16.
 */
public class Calibrator {
    private static boolean calibrated = false;
    private static Point[] triangle;
    private static HashMap<String, Point> beaconPositions = new HashMap<>();


    public void walkTheRoom(final Context context) {
        // wyświetl dialog itd.
        //Intent intent = new Intent(context, CalibrateActivity.class);

        if(true)return;

        BeaconsInRangeList birl = BeaconsInRangeList.getInstance();

        BeaconInfo[] bList = birl.getList();

        Bundle basket = new Bundle();

        String[] bListNames = new String[bList.length];
        for (int i = 0; i < bListNames.length; i++) {
            bListNames[i] = bList[i].id;
        }


        basket.putInt("quantity", bListNames.length);
        basket.putStringArray("beaconList", bListNames);

        final int ile = 3;
        final List<Integer> selected = new ArrayList<>(ile);
        AlertDialog.Builder builder =
                CalibratorMultiChoose.getBuilder(context, ile, bListNames, selected);

        AlertDialog dialog = builder.create();
        dialog.show();

        if (selected.size() != ile) // usunac w release TODO
            return;

        Map< Pair<String, String>, Double> distances = CalibratorMultiChoose.distanceMap;
        //        Map<String, BeaconInfo> map = BeaconsInRangeList.getInstance().getMap();
        // juz zakładamy że są ustawione
        double a_b = distances.get(Pair.create(bListNames[selected.get(0)], bListNames[selected.get(1)]));
        double a_c = distances.get(Pair.create(bListNames[selected.get(0)], bListNames[selected.get(2)]));
        double c_b = distances.get(Pair.create(bListNames[selected.get(1)], bListNames[selected.get(2)]));
        double c_a = distances.get(Pair.create(bListNames[selected.get(2)], bListNames[selected.get(0)]));

        triangle = LogicCalc.makeTriangle(a_b, a_c, c_b, c_a);
        Log.d("triangle", triangle[0]._X + " " + triangle[0]._Y);
        Log.d("triangle", triangle[1]._X + " " + triangle[1]._Y);
        Log.d("triangle", triangle[2]._X + " " + triangle[2]._Y);


        for (int i = 0; i <= 3; i++)
            beaconPositions.put(bListNames[selected.get(0)], triangle[0]);


        calibrated = true;

    }

    public static Map<Pair<String, String>, Double> continuusStand(Context context, String str,
                                                String otherB1, String otherB2 ) {
        Log.d("przed", "jw");
        Map< Pair<String, String>, Double> distances = new HashMap<>();
        AlertDialog stand = CalibratorStandHere.standPlease(context, str, distances,
                otherB1, otherB2, 1);
        stand.show();

        Log.d("elo", "siema");
        return distances;
    }


    public static HashMap<String, Point> deliverBeaconPositions() {
        if (!calibrated)
            return new HashMap<>(beaconPositions); // nic tylko je narysować

        return null;
    }

    public static List<Point> deliverVerticles() {
        if (!calibrated)
            return null;

        return null;
    }

    public static boolean isCalibrated() {
        return calibrated;
    }

    public static Point definePosition()
    {
        if (!calibrated)
            return null;
        Set<String> set = beaconPositions.keySet();
        ArrayList<Double> lengths = new ArrayList<>();

        for(String s: set)
            lengths.add(BeaconsInRangeList.getDistance(s));

        return null;
    }
}
