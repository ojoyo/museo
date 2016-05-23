package org.beaconmuseum.beaconmuseum.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

/**
 * Created by skazy on 23.05.16.
 */
public class CalibratorMultiChoose {

    public static AlertDialog.Builder getBuilder(final Context context, final int ile,
                                                 String[] bListNames, final List<Integer> selected) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Wybierz" + ile + "beacony do kalibracji")
                .setMultiChoiceItems(bListNames, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            int count = 0;

                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    count++;
                                    if (count <= ile)
                                        selected.add(which);
                                    else {
                                        count--;
                                        ((AlertDialog) dialog).getListView().setItemChecked(which, false);
                                    }
                                } else {
                                    if (selected.contains(which)) {
                                        // Else, if the item is already in the array, remove it
                                        selected.remove(Integer.valueOf(which));
                                    }
                                    count--;
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        if (selected.size() == ile) {
                            dialog.dismiss();
                        } else
                            Toast.makeText(context, "Wybierz dokładnie 3 beacony!", Toast.LENGTH_SHORT)
                                    .show();
                    }
                })
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        return builder;
    }
}