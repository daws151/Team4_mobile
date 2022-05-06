package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class AgentDetailsActivity extends AppCompatActivity {

    TextView tvAgentName, tvAgentEmail, tvAgentPhone, tvAgentPosition;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

        tvAgentName = findViewById(R.id.tvAgentName);
        tvAgentPhone = findViewById(R.id.tvAgentPhone);
        tvAgentEmail = findViewById(R.id.tvAgentEmail);
        tvAgentPosition = findViewById(R.id.tvAgentPosition);

        tvAgentName.setEnabled(false);
        tvAgentPhone.setEnabled(false);
        tvAgentEmail.setEnabled(false);
        tvAgentPosition.setEnabled(false);

        btnBack = findViewById(R.id.btnBack);



        Intent intent = getIntent();
        Agent agt = (Agent) intent.getSerializableExtra("agent");

//        tvAgentName.setText(agt.getAgtFirstName() + " " + agt.getAgtLastName());
        tvAgentName.setText(agt.toString());
        tvAgentPhone.setText(agt.getAgtBusPhone());
        tvAgentEmail.setText(agt.getAgtEmail());
        tvAgentPosition.setText(agt.getAgtPosition());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

    }
}