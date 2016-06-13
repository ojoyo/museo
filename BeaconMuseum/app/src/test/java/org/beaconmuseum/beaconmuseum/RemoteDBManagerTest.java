package org.beaconmuseum.beaconmuseum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by johnny on 13.06.16.
 */
public class RemoteDBManagerTest {
    RemoteDBManager manager;
    String beaconName1, beaconName2;

    @Before
    public void setUp() throws Exception {
        beaconName1 = "MnPb";
        beaconName2 = "rWmP";
        manager = new RemoteDBManager();
    }

    @Test
    // pewnie trzeba zmienic linki ale nie wiem na jakie bo students nie dziala
    public void testGetUrlFromDatabase() throws Exception {
        String res = manager.getUrlFromDatabase(beaconName1);
        String link = "https://pl.wikipedia.org/wiki/Mona_Lisa";
        assertEquals(res, link);
        res = manager.getUrlFromDatabase(beaconName2);
        link = "https://pl.wikipedia.org/wiki/Poca%C5%82unek_(obraz_Gustava_Klimta)";
        assertEquals(res,link);
    }
}
