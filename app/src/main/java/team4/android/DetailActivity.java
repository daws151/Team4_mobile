package team4.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    EditText etPackageId, etPkgName, etPkgStartDate, etPkgEndDate,
            etPkgDesc, etPkgBasePrice, etPkgAgencyCommission;
    RequestQueue requestQueue;
    Package pkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_detail);

        requestQueue = Volley.newRequestQueue(this);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etPackageId = findViewById(R.id.etPackageId);
        etPkgName = findViewById(R.id.etPkgName);
        etPkgStartDate = findViewById(R.id.etPkgStartDate);
        etPkgEndDate = findViewById(R.id.etPkgEndDate);
        etPkgDesc = findViewById(R.id.etPkgDesc);
        etPkgBasePrice = findViewById(R.id.etPkgBasePrice);
        etPkgAgencyCommission = findViewById(R.id.etPkgAgencyCommission);

        Intent intent = getIntent();
        pkg = (Package) intent.getSerializableExtra("package");

        Executors.newSingleThreadExecutor().execute(new GetPackage(pkg.getPackageId()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
                Date startDate = null, endDate = null;
                try {
                    startDate = (Date) df.parse(etPkgStartDate.getText().toString());
                    endDate = (Date) df.parse(etPkgEndDate.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Package pkg = new Package(Integer.parseInt(etPackageId.getText().toString()),
                        etPkgName.getText().toString(),
                        startDate,
                        endDate,
                        etPkgDesc.getText().toString(),
                        Double.parseDouble(etPkgBasePrice.getText().toString()),
                        Double.parseDouble(etPkgAgencyCommission.getText().toString())
                        );
                Executors.newSingleThreadExecutor().execute(new PostPackage(pkg));
           }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Executors.newSingleThreadExecutor().execute(new DeletePackage(pkg.getPackageId()));
            }
        });

    }

    class GetPackage implements Runnable {
        private int packageId;

        public GetPackage(int packageId) {
            this.packageId = packageId;
        }

        @Override
        public void run() {
            //retrieve JSON data from REST service into StringBuffer
            StringBuffer buffer = new StringBuffer();
//            String url = "http://192.168.0.27:8080/Winter2022JSPDay7REST-1.0-SNAPSHOT/api/package/getpackage/" + packageId;
            String url = "http://localhost:8081/team4_server_war_exploded/package/getpackages" + packageId;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");

                    //convert JSON data from response string into an Agent
                    JSONObject packageJSON = null;
                    try {
                        packageJSON = new JSONObject(response);
                      } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //update ListView with the adapter of Packages
                    final JSONObject finalPkg = packageJSON;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                etPackageId.setText(finalPkg.getInt("packageId") + "");
                                etPkgName.setText(finalPkg.getString("pkgName"));
                                etPkgStartDate.setText(finalPkg.getString("pkgStartDate"));
                                etPkgEndDate.setText(finalPkg.getString("pkgEndDate"));
                                etPkgDesc.setText(finalPkg.getString("pkgDesc"));
                                etPkgBasePrice.setText(finalPkg.getString("pkgBasePrice"));
                                etPkgAgencyCommission.setText(finalPkg.getString("pkgAgencyCommission"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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

    class PostPackage implements Runnable {
        private Package pkg;

        public PostPackage(Package pkg) {
            this.pkg = pkg;
        }

        @Override
        public void run() {
            //send JSON data to REST service
            String url = "http://192.168.0.27:8080/Winter2022JSPDay7REST-1.0-SNAPSHOT/api/package/postpackage";
            JSONObject obj = new JSONObject();
            SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
            String startDate = df.format(pkg.getPkgStartDate());
            String endDate = df.format(pkg.getPkgEndDate());
            try {
                obj.put("packageId", pkg.getPackageId());
                obj.put("pkgName", pkg.getPkgName());
                obj.put("pkgStartDate", startDate);
                obj.put("pkgEndDate", endDate);
                obj.put("pkgDesc", pkg.getPkgDesc());
                obj.put("pkgBasePrice", pkg.getPkgBasePrice());
                obj.put("pkgAgencyCommission", pkg.getPkgAgencyCommission());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("harv", "response=" + response);
                            VolleyLog.wtf(response.toString(), "utf-8");

                            //display result message
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_LONG).show();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("harv", "error=" + error);
                            VolleyLog.wtf(error.getMessage(), "utf-8");
                        }
                    });

            requestQueue.add(jsonObjectRequest);
        }
    }

    class DeletePackage implements Runnable {
        private int packageId;

        public DeletePackage(int packageId) {
            this.packageId = packageId;
        }

        @Override
        public void run() {
            //retrieve JSON data from REST service into StringBuffer
            StringBuffer buffer = new StringBuffer();
            String url = "http://192.168.0.27:8080/Winter2022JSPDay7REST-1.0-SNAPSHOT/api/package/deletepackage/" + packageId;
            StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    VolleyLog.wtf(response.toString(), "utf-8");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Toast.makeText(getApplicationContext(), new JSONObject(response).getString("message"), Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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