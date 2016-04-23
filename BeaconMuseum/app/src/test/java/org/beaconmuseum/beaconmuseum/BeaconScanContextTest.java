package org.beaconmuseum.beaconmuseum;

import org.beaconmuseum.beaconmuseum.beacons.BeaconScanContext;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeaconScanContextTest {
    BeaconScanContext scanContext;

    @Before
    public void setup() {
        scanContext = new BeaconScanContext();
    }

    @Test
    public void testGetScanContext() throws Exception {
        assertNotNull(scanContext.getScanContext());
    }
}