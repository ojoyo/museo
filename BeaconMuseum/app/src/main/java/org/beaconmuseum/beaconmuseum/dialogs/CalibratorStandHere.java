package org.beaconmuseum.beaconmuseum.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.beaconmuseum.beaconmuseum.beacons.BeaconsInRangeList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Created by skazy on 23.05.16.
 */
public class CalibratorStandHere {

    final private static double closeToBeacon = 0.5;

    public static AlertDialog.Builder getBuilder(final Context context,
                                                 final String standHere) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Stań przy beaconie " + standHere + " i naciśnij OK")
                .setTitle("Kalibracja")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
        ;

        return builder;
    }

    public static AlertDialog standPlease(final Context context,
                                          final String standHere,
                                          final Map<Pair<String, String>, Double> distances,
                                          final String otherB1, final String otherB2,
                                          final int method_type) {
        final AlertDialog adb = new CalibratorStandHere().getBuilder(context, standHere).create();
        adb.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button b = adb.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (BeaconsInRangeList.getDistance(standHere) > closeToBeacon)
                            Toast.makeText(context, "Stoisz za daleko!", Toast.LENGTH_SHORT).show();
                        else {
                            distances.put(Pair.create(standHere, otherB1),
                                    BeaconsInRangeList.getDistance(otherB1));
                            distances.put(Pair.create(standHere, otherB2),
                                    BeaconsInRangeList.getDistance(otherB2));

                            if(method_type == 1)
                                standPlease(context, otherB1, distances, standHere, otherB2, 2)
                                        .show();
                            else
                            {
                                Log.d("dystanse", Arrays.toString(distances.entrySet().toArray()));
                            }

                            adb.dismiss();
                            Log.d("po", "dismiss");
                        }
                    }
                });
            }

        });

        return adb;
    }

}
