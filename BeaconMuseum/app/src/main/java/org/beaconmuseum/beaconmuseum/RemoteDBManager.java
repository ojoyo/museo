package org.beaconmuseum.beaconmuseum;

import com.prmja.http.*;

import java.lang.*;
import org.json.*;

import java.util.concurrent.ExecutionException;

/**
 * Created by czarny on 23.05.16.
 */
public class RemoteDBManager {

    public RemoteDBManager() {}

    public String getUrlFromDatabase(String beaconName) {
        String res = "";
        try {
            String[] params = {"nazwa", beaconName};
            res = prmja_com.Get("http://students.mimuw.edu.pl/~sp360609/get_site_from_beacon.php", params);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        res = res.replace("\\/", "/");

        //parse json data
        try {
            JSONArray jArray = new JSONArray(res);
            JSONObject json_data = jArray.getJSONObject(0);
            res = json_data.getString("LINK");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return res;
    }

}
