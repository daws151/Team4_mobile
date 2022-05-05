package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

import java.io.InputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;


public class BookPackageActivity extends AppCompatActivity {

    ListView lvPackages;
    RequestQueue requestQueue;
    Button btnHome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_package);

        requestQueue = Volley.newRequestQueue(this);
        lvPackages = findViewById(R.id.lvPackages);
        btnHome = findViewById(R.id.btnHome);

        Executors.newSingleThreadExecutor().execute(new GetPackages());

        lvPackages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Package pkg = (Package) lvPackages.getAdapter().getItem(position);
                Intent intent = new Intent(getApplicationContext(), PackagesActivity.class);
                intent.putExtra("package", pkg);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookPackageActivity.this, HomeActivity.class));
            }
        });




    }

    private class GetPackages implements Runnable {

        @Override
        public void run() {
            StringBuffer buffer = new StringBuffer();
            String url = "http://localhost:8081/team4_server_war_exploded/package/getpackages";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");


                    ArrayAdapter<Package> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                            Date startDate = (Date) dateFormat.parse(obj.getString("pkgStartDate"));
                            Log.d("William", startDate.toString());
                            Date endDate = (Date) dateFormat.parse(obj.getString("pkgEndDate"));
                            Package pkg = new Package(obj.getInt("packageId"),
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