package team4.android;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class AddActivity extends AppCompatActivity {
    Button btnSaveAdd;
    EditText etPkgNameAdd, etPkgStartDateAdd, etPkgEndDateAdd,
            etPkgDescAdd, etPkgBasePriceAdd, etPkgAgencyCommissionAdd;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        requestQueue = Volley.newRequestQueue(this);

        btnSaveAdd = findViewById(R.id.btnSaveAdd);
        etPkgNameAdd = findViewById(R.id.etPkgNameAdd);
        etPkgStartDateAdd = findViewById(R.id.etPkgStartDateAdd);
        etPkgEndDateAdd = findViewById(R.id.etPkgEndDateAdd);
        etPkgDescAdd = findViewById(R.id.etPkgDescAdd);
        etPkgBasePriceAdd = findViewById(R.id.etPkgBasePriceAdd);
        etPkgAgencyCommissionAdd = findViewById(R.id.etPkgAgencyCommissionAdd);

        btnSaveAdd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                Date startDate = null, endDate = null;
                try {
                    startDate = (Date) df.parse(etPkgStartDateAdd.getText().toString());
                    endDate = (Date) df.parse(etPkgEndDateAdd.getText().toString());


                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Package pkg = new Package(ThreadLocalRandom.current().nextInt(),
                        etPkgNameAdd.getText().toString(),
                        startDate,
                        endDate,
                        etPkgDescAdd.getText().toString(),
                        Double.parseDouble(etPkgBasePriceAdd.getText().toString()),
                        Double.parseDouble(etPkgAgencyCommissionAdd.getText().toString())
                );
                Executors.newSingleThreadExecutor().execute(new PutPackage(pkg));
            }
        });
    }

    class PutPackage implements Runnable {
        private Package pkg;

        public PutPackage(Package pkg) {
            this.pkg = pkg;
        }

        @Override
        public void run() {
            //send JSON data to REST service
            String url = "http://localhost:8081/rest/insertpackage";
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
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, obj,
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


}
