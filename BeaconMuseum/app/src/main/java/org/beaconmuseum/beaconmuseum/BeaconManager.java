package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.manager.KontaktProximityManager;

import android.content.Context;

public class BeaconManager {
    private int counter;
    private KontaktProximityManager proximityManager;

    public void initialize(Context context) {
        ++this.counter;

        // Start KontaktSDK
        int kontaktApiResourceId = R.string.kontakt_io_api_key;
        String apiKey = context.getString(kontaktApiResourceId);
        KontaktSDK.initialize(apiKey);

        // Create proximity manager and register listener
        this.proximityManager = new KontaktProximityManager(context);
        proximityManager.initializeScan(BeaconScanContext.getScanContext(), new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.attachListener(BeaconEventListener.getInstance());
            }

            @Override
            public void onConnectionFailure() {

            }
        });
    }

    public KontaktProximityManager getProximityManager() {
        return this.proximityManager;
    }

    public void destroy() {
        --this.counter;

        if(this.counter > 0 || this.proximityManager == null)
            return;

        proximityManager.detachListener(BeaconEventListener.getInstance());
        this.proximityManager.disconnect();
    }
}
