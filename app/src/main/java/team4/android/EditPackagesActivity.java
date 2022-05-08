package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This is the start point of the application, it gives access to the Customer or Employee pages.
 ***/

public class EditPackagesActivity extends AppCompatActivity {

    /*** REPLACE WITH IP ADDRESS OF REST SERVICE ***/
    String ip = "192.168.1.89:8081";
    RequestQueue requestQueue;
    ListView lvPackages;
    Button btnEmployeeHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_packages);

        requestQueue = Volley.newRequestQueue(this);
        lvPackages = findViewById(R.id.lvPackages);
        btnEmployeeHome = findViewById(R.id.btnEmployeeHome);

        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EditPackageDetailsActivity.class);
                intent.putExtra("package", (Package)lvPackages.getAdapter().getItem(i));
                startActivity(intent);
            }
        });

        btnEmployeeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditPackagesActivity.this, EmployeeHomeActivity.class));
            }
        });

        Executors.newSingleThreadExecutor().execute(new EditPackagesActivity.GetPackages());
    }

    private class GetPackages implements Runnable {

        @Override
        public void run() {
            StringBuffer buffer = new StringBuffer();
            String url = "http://" + ip + "/team4_server_war_exploded/package/getpackages";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");

                    ArrayAdapter<Package> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                            // These variables are in place to catch any null values from the database.
                            Date sDate = dateFormat.parse("1970-01-01");
                            Date eDate = dateFormat.parse("1970-01-01");
                            String desc = "N/A";
                            Double comm = 0.0;

                            // If statements used to gather correct variable value if they exist, otherwise will use the variables above.
                            if (obj.has("pkgStartDate")) {
                                sDate = dateFormat.parse(obj.getString("pkgStartDate"));
                            }
                            if (obj.has("pkgEndDate")) {
                                eDate = dateFormat.parse(obj.getString("pkgEndDate"));
                            }
                            if (obj.has("pkgAgencyCommission")) {
                                comm = obj.getDouble("pkgAgencyCommission");
                            }
                            if (obj.has("pkgDesc")) {
                                desc = obj.getString("pkgDesc");
                            }

                            // Package object builder
                            Package pkg = new Package(obj.getInt("PackageId"),
                                    obj.getString("pkgName"),
                                    sDate,
                                    eDate,
                                    desc,
                                    obj.getDouble("pkgBasePrice"),
                                    comm
                            );
                            adapter.add(pkg);
                        }
                    } catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }

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

