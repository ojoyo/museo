package org.beaconmuseum.beaconmuseum;

import android.util.Log;

import com.google.inject.AbstractModule;
import com.kontakt.sdk.android.ble.discovery.EventType;

import org.beaconmuseum.beaconmuseum.beacons.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import roboguice.RoboGuice;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * Created by johnny on 13.06.16.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class BeaconSwitchSettingsTest {
    BeaconSwitchSettings beaconSwitch;
    NearestBeacon nearestBeaconMock;
    BeaconInfo beacon1, beacon2;
    NearestBeacon info;

    @Before
    public void setUp() throws Exception {
        beaconSwitch = new BeaconSwitchSettings();
        nearestBeaconMock = mock(NearestBeacon.class);
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new MyTestModule());
        beaconSwitch = RoboGuice.getInjector(RuntimeEnvironment.application).getInstance(BeaconSwitchSettings.class);
        beacon1 = new BeaconInfo("id1", "beacon 1", 20.0);
        beacon2 = new BeaconInfo("id2", "beacon 2", 30.0);

    }

    @After
    public void tearDown() throws Exception {
        RoboGuice.Util.reset();
    }

    @Test
    public void testToggleMode() throws Exception {
        assertFalse(beaconSwitch.isManualModeOn());
        beaconSwitch.toggleMode();
        assertFalse(!beaconSwitch.isManualModeOn());
    }

    @Test
    public void testUpdateLastNearestBeacon() throws Exception {
        when(nearestBeaconMock.getInfo()).thenReturn(beacon1);
        beaconSwitch.updateLastNearestBeacon();
        assertEquals(beaconSwitch.getLastNearestBeacon(), beacon1);
    }

    @Test
    public void testNearestBeaconHasChanged() throws Exception {
        when(nearestBeaconMock.getInfo()).thenReturn(beacon1);
        beaconSwitch.updateLastNearestBeacon();
        assertFalse(beaconSwitch.nearestBeaconHasChanged());
        when(nearestBeaconMock.getInfo()).thenReturn(beacon2);
        assertTrue(beaconSwitch.nearestBeaconHasChanged());

    }

    @Test
    public void testIsManualModeOn() throws Exception {
        assertFalse(beaconSwitch.isManualModeOn());
        beaconSwitch.toggleMode();
        assertTrue(beaconSwitch.isManualModeOn());
    }

    @Test
    public void testGetLastNearestBeacon() {
        when(nearestBeaconMock.getInfo()).thenReturn(beacon1);
        beaconSwitch.updateLastNearestBeacon();
        assertEquals(beaconSwitch.getLastNearestBeacon(), beacon1);
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(NearestBeacon.class).toInstance(nearestBeaconMock);
        }
    }

}
