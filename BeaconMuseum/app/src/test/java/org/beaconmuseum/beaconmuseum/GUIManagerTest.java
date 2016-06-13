package org.beaconmuseum.beaconmuseum;

import android.app.Activity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by johnny on 13.06.16.
 */
public class GUIManagerTest {

    Activity activity;
    String beaconName1, beaconName2;

    @Test
    // moze nie dzialac albo zle linki bo students nie dziala, jak to pisze
    public void getBeaconLink() throws Exception {
        GUIManager manager = new GUIManager(activity);
        beaconName1 = "MnPb";
        beaconName2 = "rWmP";

        String res = manager.getBeaconLink(beaconName1);
        String link = "https://pl.wikipedia.org/wiki/Mona_Lisa";
        assertEquals(res, link);
        res = manager.getBeaconLink(beaconName2);
        link = "https://pl.wikipedia.org/wiki/Poca%C5%82unek_(obraz_Gustava_Klimta)";
        assertEquals(res,link);
    }
}
