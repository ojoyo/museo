package org.beaconmuseum.beaconmuseum;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by skazy on 22.05.16.
 */

class MyAdapter extends ArrayAdapter<String> {
    public MyAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MyAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    //todo
}

public class CalibrateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calibrator_assistent);

        Bundle beaconsToShow = getIntent().getExtras();

        int howMany = beaconsToShow.getInt("quantity");

        String bList[] = beaconsToShow.getStringArray("beaconList");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, bList);

        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listView.isItemChecked(position)) {
                    listView.setItemChecked(position, true);
                    Log.d("check", "robie check na " + position);
                }
                else {
                    listView.setItemChecked(position, false);
                    Log.d("check", "robie uncheck na " + position);
                }

            }
        });

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
