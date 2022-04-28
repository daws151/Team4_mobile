package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lvPackages;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    class GetPackages implements Runnable {
        @Override
        public void run() {
            //retrieve JSON data from REST service into StringBuffer
            StringBuffer buffer = new StringBuffer();
            String url = "TBD"; // Brett - SET UP REST SERVICE AND PUT URL HERE
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");

                    //convert JSON data from response string into an ArrayAdapter of Packages
                    ArrayAdapter<Package> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++)
                        {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
                            Date startDate = (Date) df.parse(obj.getString("pkgStartDate"));
                            Log.d("brett", startDate.toString());
                            Date endDate = (Date) df.parse(obj.getString("pkgEndDate"));
                            Package pkg = new Package(obj.getInt("packageId"),
                                    obj.getString("pkgName"),
                                    startDate,
                                    endDate,
                                    obj.getString("pkgDesc"),
                                    obj.getDouble("pkgBasePrice"),
                                    obj.getDouble("pkgBasePrice")
                            );
                            adapter.add(pkg);
                        }
                    } catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }

                    //update ListView with the adapter for Packages
                    final ArrayAdapter<Package> finalAdapter = adapter;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lvPackages.setAdapter(finalAdapter);
                        }
                    });
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.wtf(error.getMessage(), "utf-8");
                }
            });

            requestQueue.add(stringRequest);
        }
    }
}