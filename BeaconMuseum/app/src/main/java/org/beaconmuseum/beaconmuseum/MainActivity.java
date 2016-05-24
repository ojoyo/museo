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

    BtBroadcastReceiver bluetoothState = new BtBroadcastReceiver();
    RemoteDBManager dbManager = new RemoteDBManager();

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

    protected void updateSlideMenu() {
        HorizontalScrollView slideMenu = (HorizontalScrollView) findViewById(R.id.scrollView);
        ArrayList<String> list = new ArrayList<>();
        for (BeaconInfo beacon : appManager.refreshGUI()) {
            list.add(beacon.id);
            Log.d("update slide", beacon.id);
        }
//        list.add("Just"); list.add("adding"); list.add("some");
//        list.add("words"); list.add("to"); list.add("make"); list.add("you"); list.add("see");
//        list.add("it"); list.add("slides");

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        if(slideMenu == null)
            return;
        slideMenu.removeAllViews();
        slideMenu.addView(ll);

        for (String str: list) {
            Button b = new Button(this);
            b.setText(str);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayAnotherPainting(v);
                }
            });
            ll.addView(b);
        }

        //this.setContentView(slideMenu);
//        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
//                this,
//                android.R.layout.simple_list_item_1,
//                list);
//        if (slideMenu == null)
//            return;
//        if (slideMenu.getChildCount() == 0)
//            return;
//        ViewGroup parent = (ViewGroup) slideMenu.getChildAt(0);
//        parent.removeAllViews();
//        final int adapterCount = listAdapter.getCount();
//        for (int i = 0; i < adapterCount; i++) {
//            parent.addView(listAdapter.getView(i, null, parent));
//        }
    }

    private void displayAnotherPainting(View v) {
        Button b = (Button) v;
        String beaconName = b.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothState.checkBluetoothConnection(this);

        beaconManager.initialize(this);
        eventListener.registerProcessor(this);

        initializeBrowser();
        updateSlideMenu();
    }

    @Override
    public void processBeaconEvent(EventType event, BeaconInfo beacon) {
        updateSlideMenu();
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
