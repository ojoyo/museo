package org.beaconmuseum.beaconmuseum;

import android.widget.CompoundButton;

import com.google.inject.Inject;
import org.beaconmuseum.beaconmuseum.beacons.BeaconSwitchSettings;

public class ToggleManualMode implements CompoundButton.OnCheckedChangeListener {
    @Inject private BeaconSwitchSettings settings;

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked != settings.isManualModeOn())
            settings.toggleMode();
        settings.displayNearestPainting();
    }

    public BeaconSwitchSettings getSettings() {
        return settings;
    }
}
