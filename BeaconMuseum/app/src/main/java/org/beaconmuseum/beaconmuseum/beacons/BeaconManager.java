package org.beaconmuseum.beaconmuseum.beacons;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import android.content.Context;

/**
 * Używany do uruchomienia i zatrzymania SDK. Należy użyć przed użyciem jakiejkolwiek
 * innej metody korzystającej z SDK.
 */
@Singleton
public class BeaconManager {
    @Inject ProximityManagerWrapper proximityManager;
    @Inject BeaconScanContext scanContext;
    @Inject BeaconEventListener eventListener;
    @Inject KontaktSDKWrapper kontaktSDK;
    @Inject BeaconsInRangeList beaconsInRangeList;

    /**
     * Przygotowuje SDK i uruchamia nasłuchiwanie beaconów.
     *
     * @param context Referencja do contextu lub obiektu Activity która uruchamia
     *                nasłuchiwanie.
     */
    public void initialize(Context context) {
        kontaktSDK.initialize(context);

        eventListener.registerProcessor(beaconsInRangeList);

        proximityManager.makeManager(context);
        proximityManager.initializeScan(scanContext.getScanContext(), new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.attachListener(eventListener);
            }

            @Override
            public void onConnectionFailure() {

            }
        });
    }

    /**
     * Zatrzymuje nasłuchiwanie i wyłącza SDK.
     */
    public void destroy() {
        if(!proximityManager.isCreated())
            return;

        proximityManager.detachListener(eventListener);
        proximityManager.disconnect();

        kontaktSDK.reset();
    }
}
