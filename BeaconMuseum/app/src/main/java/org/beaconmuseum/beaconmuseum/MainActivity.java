package org.beaconmuseum.beaconmuseum;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import com.google.inject.Inject;
import com.kontakt.sdk.android.ble.discovery.EventType;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import org.beaconmuseum.beaconmuseum.beacons.*;
import org.beaconmuseum.beaconmuseum.locator.IndoorLocator;

import java.util.ArrayList;

public class MainActivity extends RoboActivity implements BeaconEventProcessorInterface {
    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Inject BeaconManager beaconManager;
    @Inject AppManager appManager;
    @Inject BeaconEventListener eventListener;
    @Inject BeaconSwitchSettings beaconSwitchSettings;

    BtBroadcastReceiver bluetoothState = new BtBroadcastReceiver();

    private class ArtBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    protected void initializeBrowser() {
        WebView closestPainting = (WebView) findViewById(R.id.webView);
        closestPainting.setWebViewClient(new ArtBrowser());
        closestPainting.loadUrl("https://pl.wikipedia.org/wiki/Zdzis%C5%82aw_Beksi%C5%84ski");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothState.checkBluetoothConnection(this);

        beaconManager.initialize(this);
        eventListener.registerProcessor(this);

        initializeBrowser();
        BeaconSwitchSettings._activity = this;
        beaconSwitchSettings.updateSlideMenu(this);
    }

    @Override
    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        if (event == EventType.DEVICE_DISCOVERED) {
            Log.d("device discovered", beacon.id);
            beaconSwitchSettings.updateSlideMenu(this);
        }
        if (event == EventType.DEVICE_LOST) {
            Log.d("device lost", beacon.id);
            beaconSwitchSettings.updateSlideMenu(this);
        }
        //refreshClick(null);
    }

    /*TODO tutaj będziemy apdejtować i listę, i stronę najbliższego beacona*/
    /*public void refreshClick(View v) {
        BeaconInfo[] bList = appManager.refreshGUI();
        TextView textViewBig = (TextView)findViewById(R.id.textView);
        if(textViewBig == null)
            return;

        for (int i = 0; i < bList.length; i++) {
            Log.d("rangelist", bList[i].id + ", odleglosc: " + bList[i].range + "\n");
            if (i == 0)
                textViewBig.setText(bList[i].id);
            //TODO else inne beacony w dynamicznej liscie pod spodem (na razie jest statyczna!)
            // uzywac funkcji GUIManagera
        }
        Log.d("rangelist", "____\n");

    }*/

    public void changeVIewTEst(View v) {
        setContentView(R.layout.calibrator_assistent);

        IndoorLocator.init(this);
        IndoorLocator.calibrate();

    }

}
