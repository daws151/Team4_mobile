package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This page loads select details of a specified customer. Functions to update the database not implemented due to time constraints.
 ***/
public class CustomerDetailsActivity extends AppCompatActivity {

    TextView tvCustomerName, tvCustAddress, tvCustCity, tvCustPostal, tvCustHomePhone, tvCustBusPhone, tvCustEmail, tvCustAgentID;

    Button btnBack;

//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        tvCustomerName = findViewById(R.id.tvCustomerName);
        tvCustAddress = findViewById(R.id.tvCustAddress);
        tvCustCity = findViewById(R.id.tvCustCity);
        tvCustPostal = findViewById(R.id.tvCustPostal);
        tvCustHomePhone = findViewById(R.id.tvCustHomePhone);
        tvCustBusPhone = findViewById(R.id.tvCustBusPhone);
        tvCustEmail = findViewById(R.id.tvCustEmail);
        tvCustAgentID = findViewById(R.id.tvCustAgentID);

        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Customer cust = (Customer) intent.getSerializableExtra("customer");

        // preps both Home phone and Business phone for Dialer intent
        Uri hphone = Uri.parse("tel:" + cust.getCustHomePhone());
        Uri bphone = Uri.parse("tel:" + cust.getCustBusPhone());

        Intent hdialer = new Intent(Intent.ACTION_DIAL, hphone);
        Intent bdialer = new Intent(Intent.ACTION_DIAL, bphone);

        tvCustomerName.setText(cust.toString());
        tvCustAddress.setText(cust.getCustAddress());
        tvCustCity.setText(cust.getCustCity());
        tvCustPostal.setText(cust.getCustPostal());
        tvCustHomePhone.setText(cust.getCustHomePhone());
        tvCustBusPhone.setText(cust.getCustBusPhone());
        tvCustEmail.setText(cust.getCustEmail());
        tvCustAgentID.setText("Agent ID: " + cust.getAgentId());

        // Enables the phone number to redirect to phone dialer app when tapped
        tvCustHomePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(hdialer);
            }
        });

        // Enables the phone number to redirect to phone dialer app when tapped
        tvCustBusPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(bdialer);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

    }
}