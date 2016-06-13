package org.beaconmuseum.beaconmuseum;

import android.app.Activity;
import android.widget.TextView;

public class GUIManager {

    public GUIManager(Activity activity){
        _activity = activity;
    }

    public static Activity _activity;
    private static RemoteDBManager dbManager = new RemoteDBManager();

    public static String getBeaconLink(String beaconName) {
        return dbManager.getUrlFromDatabase(beaconName);
    }

}

