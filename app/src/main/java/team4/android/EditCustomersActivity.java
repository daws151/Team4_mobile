package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.concurrent.Executors;

public class EditCustomersActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ListView lvCustomers;
    Button btnEmployeeHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customers);

        requestQueue = Volley.newRequestQueue(this);
        lvCustomers = findViewById(R.id.lvAgents);
        btnEmployeeHome = findViewById(R.id.btnEmployeeHome);

        Executors.newSingleThreadExecutor().execute(new EditCustomersActivity.GetCustomers());


        btnEmployeeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditCustomersActivity.this, EmployeeHomeActivity.class));
            }
        });

        lvCustomers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), CustomerDetailsActivity.class);
                intent.putExtra("customer", (Customer)lvCustomers.getAdapter().getItem(i));
                startActivity(intent);
            }
        });

    }

    private class GetCustomers implements Runnable {

        @Override
        public void run() {
            StringBuffer buffer = new StringBuffer();
            String url = "http://192.168.1.89:8081/team4_server_war_exploded/customer/getcustomers";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");

                    ArrayAdapter<Customer> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Customer cust = new Customer(obj.getInt("customerId"),
                                                      obj.getString("custFirstName"),
                                                      obj.getString("custLastName"),
                                                      obj.getString("custAddress"),
                                                      obj.getString("custCity"),
                                                      obj.getString("custProv"),
                                                      obj.getString("custPostal"),
                                                      obj.getString("custCountry"),
                                                      obj.getString("custHomePhone"),
                                                      obj.getString("custBusPhone"),
                                                      obj.getString("custEmail"),
                                                      obj.getInt("agent")
                            );
                            adapter.add(cust);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final ArrayAdapter<Customer> finalAdapter = adapter;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lvCustomers.setAdapter(finalAdapter);
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