package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.manager.KontaktProximityManager;

import android.content.Context;

/**
 * Używany do uruchomienia i zatrzymania SDK. Należy użyć przed użyciem jakiejkolwiek
 * innej metody korzystającej z SDK.
 */
public class BeaconManager {
    private static KontaktProximityManager proximityManager;

    /**
     * Przygotowuje SDK i uruchamia nasłuchiwanie beaconów.
     *
     * @param context Referencja do contextu lub obiektu Activity która uruchamia
     *                nasłuchiwanie.
     */
    public static void initialize(Context context) {
        if(proximityManager != null)
            return;

        // Start KontaktSDK
        KontaktSDK.initialize(context);

        // Create proximity manager and register listener
        proximityManager = new KontaktProximityManager(context);
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

    /**
     * Zatrzymuje nasłuchiwanie i wyłącza SDK.
     */
    public static void destroy() {
        if(proximityManager == null)
            return;

        proximityManager.detachListener(BeaconEventListener.getInstance());
        proximityManager.disconnect();
        proximityManager = null;

        KontaktSDK.reset();
    }
}
