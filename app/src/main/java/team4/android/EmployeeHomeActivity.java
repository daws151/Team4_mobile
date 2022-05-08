package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This is the Home page for employees.
 ***/
public class EmployeeHomeActivity extends AppCompatActivity {


    TextView tvEmpWelcome;

    Button btnEditPackages, btnEditCustomers, btnEmpLogout;

    // This variable was meant to catch user's name from the login which we ended up abandoning due to time.
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);


        btnEditPackages = findViewById(R.id.btnEditPackages);
        btnEditCustomers = findViewById(R.id.btnEditCustomers);
        btnEmpLogout = findViewById(R.id.btnEmpLogout);
        tvEmpWelcome = findViewById(R.id.tvEmpWelcome);

        // Header text, when set up will display the user's name
        tvEmpWelcome.setText("Welcome" + username + "!");

        btnEditPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomeActivity.this, EditPackagesActivity.class));
            }
        });

        btnEditCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomeActivity.this, EditCustomersActivity.class));
            }
        });

        btnEmpLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomeActivity.this, WelcomeActivity.class));
            }
        });

    }
}