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
import android.widget.Toast;

import org.beaconmuseum.beaconmuseum.AppManager;
import org.beaconmuseum.beaconmuseum.CalibrateActivity;
import org.beaconmuseum.beaconmuseum.beacons.BeaconInfo;
import org.beaconmuseum.beaconmuseum.beacons.BeaconManager;
import org.beaconmuseum.beaconmuseum.beacons.BeaconsInRangeList;
import org.beaconmuseum.beaconmuseum.dialogs.CalibratorMultiChoose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skazy on 20.05.16.
 */
public class Calibrator {
    private static boolean calibrated = false;

    public void walkTheRoom(final Context context) {
        // wyświetl dialog itd.
        //Intent intent = new Intent(context, CalibrateActivity.class);

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

        if (selected.size() != ile)
            return;
        Log.d("calib", "po dialogu");

        //intent.putExtras(basket);
        //context.startActivity(intent);

        BeaconInfo w1, w2, w3;
        Map<String, BeaconInfo> map = BeaconsInRangeList.getInstance().getMap();
        w1 = map.get(bListNames[selected.get(0)]);
        w2 = map.get(bListNames[selected.get(1)]);
        w3 = map.get(bListNames[selected.get(2)]);
        // juz zakładamy że są ustawione

        Log.d("binfo", w1.id + w2.id + w3.id);


        calibrated = true;
    }

    public static HashMap<String, Point> deliverBeaconPositions() {
        if (!calibrated)
            return null;

        return null;
    }

    public static List<Point> deliverVerticles() {
        if (!calibrated)
            return null;

        return null;
    }

}
