package org.beaconmuseum.beaconmuseum;

import com.kontakt.sdk.android.ble.configuration.*;
import com.kontakt.sdk.android.ble.configuration.scan.*;
import com.kontakt.sdk.android.ble.manager.ProximityManager;

/**
 * Pozwala manipulować parametrami skanowania otoczenia pod kątem wyszukiwania beaconów.
 */
public class BeaconScanContext {
    public static ScanContext getScanContext() {
        return new ScanContext.Builder()
                .setScanPeriod(ScanPeriod.RANGING)
                // or for monitoring for 15 seconds scan and 10 seconds waiting:
                //.setScanPeriod(new ScanPeriod(TimeUnit.SECONDS.toMillis(15), TimeUnit.SECONDS.toMillis(10)))
                .setScanMode(ProximityManager.SCAN_MODE_BALANCED)
                .setActivityCheckConfiguration(ActivityCheckConfiguration.MINIMAL)
                .setForceScanConfiguration(ForceScanConfiguration.MINIMAL)
                .setIBeaconScanContext(new IBeaconScanContext.Builder().build())
                .setEddystoneScanContext(new EddystoneScanContext.Builder().build())
                .setForceScanConfiguration(ForceScanConfiguration.MINIMAL)
                .build();
    }
}
