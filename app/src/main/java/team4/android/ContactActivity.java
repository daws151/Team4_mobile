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
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This application shows all agents, redirects when clicked to provide that agent's contact information.
 ***/
public class ContactActivity extends AppCompatActivity {

    /*** REPLACE WITH IP ADDRESS OF REST SERVICE ***/
    String ip = "192.168.1.89:8081";

    RequestQueue requestQueue;
    Button btnHome;
    ListView lvAgents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        requestQueue = Volley.newRequestQueue(this);
        btnHome = findViewById(R.id.btnHome);
        lvAgents = findViewById(R.id.lvAgents);

        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AgentDetailsActivity.class);
                intent.putExtra("agent", (Agent)lvAgents.getAdapter().getItem(i));
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContactActivity.this, HomeActivity.class));
            }
        });

        Executors.newSingleThreadExecutor().execute(new ContactActivity.GetAgents());

    }

    private class GetAgents implements Runnable {

        @Override
        public void run() {
            StringBuffer buffer = new StringBuffer();
            String url = "http://" + ip + "/team4_server_war_exploded/agent/getagents";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    VolleyLog.wtf(response, "utf-8");

                    ArrayAdapter<Agent> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            // These variables are meant to catch any null values from the database.
                            String first = "";
                            String last = "";
                            String middle = "";
                            String phone = "";
                            String email = "";
                            String position = "";
                            Integer agency = 0;

                            // These If statements will insert correct Agent information if available. If unavailable or null values, the variables above are used.
                            if (obj.has("agtFirstName")){ first = obj.getString("agtFirstName"); }
                            if (obj.has("agtMiddleInitial")){ middle = obj.getString("agtMiddleInitial"); }
                            if (obj.has("agtLastName")){ last = obj.getString("agtLastName"); }
                            if (obj.has("agtBusPhone")){ phone = obj.getString("agtBusPhone"); }
                            if (obj.has("agtEmail")){ email = obj.getString("agtEmail"); }
                            if (obj.has("agtPosition")){ position = obj.getString("agtPosition"); }
                            if (obj.has("agencyId")){ agency = obj.getInt("agencyId"); }


                            // Agent object builder
                            Agent agt = new Agent(obj.getInt("AgentId"),
                                                  first,
                                                  middle,
                                                  last,
                                                  phone,
                                                  email,
                                                  position,
                                                  agency,
                                                  obj.getString("agtUserId"),
                                                  obj.getString("agtPassword")
                                    );
                            adapter.add(agt);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final ArrayAdapter<Agent> finalAdapter = adapter;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lvAgents.setAdapter(finalAdapter);
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