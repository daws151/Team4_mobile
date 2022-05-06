package team4.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class PackagesActivity extends AppCompatActivity {
    ListView lvPackages;
    RequestQueue requestQueue;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        requestQueue = Volley.newRequestQueue(this);
        lvPackages = findViewById(R.id.lvPackages);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PackagesActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Package pkg = (Package) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(PackagesActivity.this, DetailActivity.class);
                intent.putExtra("package", pkg);
                startActivity(intent);
            }
        });

        Executors.newSingleThreadExecutor().execute(new GetPackages());
    }

    class GetPackages implements Runnable {
        @Override
        public void run() {
            //retrieve JSON data from REST service into StringBuffer
            StringBuffer buffer = new StringBuffer();
            String url = "http://192.168.1.84:8081/team4_server_war_exploded/package/getpackages"; // Brett - REST service not running at the moment
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
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            Date startDate = (Date) df.parse(obj.getString("pkgStartDate"));
                            Log.d("brett", startDate.toString());
                            Date endDate = (Date) df.parse(obj.getString("pkgEndDate"));
                            Package pkg = new Package(obj.getInt("PackageId"),
                                    obj.getString("pkgName"),
                                    startDate,
                                    endDate,
                                    obj.getString("pkgDesc"),
                                    obj.getDouble("pkgBasePrice"),
                                    obj.getDouble("pkgAgencyCommission")
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