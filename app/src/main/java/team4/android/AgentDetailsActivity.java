package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: Agent class from the Travel Experts database. Has columns added for User ID and Password.
 ***/

public class AgentDetailsActivity extends AppCompatActivity {

    TextView tvAgentEmail, tvAgentPhone, tvAgentPosition, tvAgent;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

        tvAgentPhone = findViewById(R.id.tvCustHomePhone);
        tvAgentEmail = findViewById(R.id.tvCustEmail);
        tvAgentPosition = findViewById(R.id.tvCustAddress);
        tvAgent = findViewById(R.id.tvAgent);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Agent agt = (Agent) intent.getSerializableExtra("agent");

        // Preps the phone number for use in the Intent below.
        Uri phone = Uri.parse("tel:" + agt.getAgtBusPhone());

        // Intent to create a clickable phone number.
        Intent dialer = new Intent(Intent.ACTION_DIAL, phone);

        // Header text, uses clicked agent's first and last name
        tvAgent.setText(Html.fromHtml("<b> <i>" + agt.toString() + "</i> </b>"));

        // Sets and underline's the agent's phone number
        tvAgentPhone.setText(Html.fromHtml("<u>" + agt.getAgtBusPhone() + "</u>"));
        tvAgentEmail.setText(agt.getAgtEmail());
        tvAgentPosition.setText(agt.getAgtPosition());


        // Activates phone dialer when tapping the Agent's phone number
        tvAgentPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(dialer);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

    }
}