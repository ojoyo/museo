package org.beaconmuseum.beaconmuseum.beacons;

import android.content.Context;
import com.kontakt.sdk.android.common.KontaktSDK;

public class KontaktSDKWrapper {
    public void initialize(Context context) {
        KontaktSDK.initialize(context);
    }

    public void reset() {
        KontaktSDK.reset();
    }
}
