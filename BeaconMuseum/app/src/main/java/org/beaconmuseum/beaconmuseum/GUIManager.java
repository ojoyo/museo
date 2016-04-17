package org.beaconmuseum.beaconmuseum;

import android.app.Activity;
import android.widget.TextView;

public class GUIManager {

    public GUIManager(Activity activity){
        _activity = activity;
    }

    public static Activity _activity;

    public static void swapBeacons(TextView text1, TextView text2) {
        CharSequence tmp = text1.getText();
        text1.setText(text2.getText());
        text2.setText(tmp);
    }

    public static void swapWithClosest(TextView text) {
        TextView t = (TextView) _activity.findViewById(R.id.textView);
        swapBeacons(text,t);
    }
}
