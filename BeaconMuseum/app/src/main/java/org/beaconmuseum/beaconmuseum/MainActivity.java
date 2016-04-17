package org.beaconmuseum.beaconmuseum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kontakt.sdk.android.common.KontaktSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test if compiles:
        // KontaktSDK.initialize("YOUR API KEY");

        BeaconManager.initialize(this);
    }

    public void refreshClick(View v) {
        BeaconInfo[] bList = AppManager.refreshGUI();
        TextView textViewBig = (TextView)findViewById(R.id.textView);
        for (int i = 0; i < bList.length; i++)
            if (i == 0)
                textViewBig.setText(bList[i].id);
            //TODO else inne beacony w dynamicznej liscie pod spodem (na razie jest statyczna!)
            // uzywac funkcji GUIManagera
    }
}
