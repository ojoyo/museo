package org.beaconmuseum.beaconmuseum;

import android.util.Log;


public class AppManager {

    public static void refresh(){
        BeaconsInRangeList beaconsInRangeList = BeaconsInRangeList.getInstance();
        if (beaconsInRangeList == null) {
            Log.d("AppMgr", "null range lista!");
        }
        else {
            BeaconInfo bList[] = beaconsInRangeList.getList();
            if (bList == null)
                Log.d("ERR", "blist is null");
            else
                for (BeaconInfo bi : bList) {
                    Log.d("REFRESH", bi.id);

            }
        }
    }
}
