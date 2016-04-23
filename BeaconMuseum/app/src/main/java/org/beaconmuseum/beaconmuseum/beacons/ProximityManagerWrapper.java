package org.beaconmuseum.beaconmuseum.beacons;

import android.content.Context;

import com.kontakt.sdk.android.ble.configuration.scan.ScanContext;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.manager.KontaktProximityManager;

public class ProximityManagerWrapper {
    private KontaktProximityManager proximityManager;

    public KontaktProximityManager makeManager(Context context) {
        proximityManager = new KontaktProximityManager(context);
        return proximityManager;
    }

    public KontaktProximityManager getManager() {
        return proximityManager;
    }

    public void initializeScan(ScanContext scanContext, OnServiceReadyListener listener) {
        proximityManager.initializeScan(scanContext, listener);
    }

    public void attachListener(ProximityManager.ProximityListener listener) {
        proximityManager.attachListener(listener);
    }

    public boolean isCreated() {
        return proximityManager != null;
    }

    public void disconnect() {
        proximityManager.disconnect();
    }

    public void detachListener(ProximityManager.ProximityListener eventListener) {
        proximityManager.detachListener(eventListener);
    }
}
