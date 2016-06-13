package org.beaconmuseum.beaconmuseum;

import android.widget.CompoundButton;

import com.google.inject.AbstractModule;

import org.beaconmuseum.beaconmuseum.beacons.BeaconSwitchSettings;
import org.beaconmuseum.beaconmuseum.beacons.NearestBeacon;
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
public class ToggleManualModeTest {
    ToggleManualMode man;
    BeaconSwitchSettings beaconSwitchMock;
    CompoundButton btn;
    boolean isChecked;

    @Before
    public void setUp() throws Exception {
        man = new ToggleManualMode();
        isChecked = false;
        beaconSwitchMock = mock(BeaconSwitchSettings.class);
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new MyTestModule());
        man = RoboGuice.getInjector(RuntimeEnvironment.application).getInstance(ToggleManualMode.class);
    }

    @Test
    public void testOnCheckedChanged() throws Exception {
        when(beaconSwitchMock.isManualModeOn()).thenReturn(true);
        man.onCheckedChanged(btn,!isChecked);
        assertTrue(man.getSettings().isManualModeOn());
        when(beaconSwitchMock.isManualModeOn()).thenReturn(false);
        man.onCheckedChanged(btn,isChecked);
        assertFalse(man.getSettings().isManualModeOn());
    }

    public class MyTestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(BeaconSwitchSettings.class).toInstance(beaconSwitchMock);
        }
    }

}
