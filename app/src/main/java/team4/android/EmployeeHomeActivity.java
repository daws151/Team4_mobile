package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeHomeActivity extends AppCompatActivity {


    TextView tvEmpWelcome;

    Button btnEditPackages, btnEditBookings, btnEditCustomers, btnEmpLogout;

    String username = "";                                         // If possible, gather the Customer's first name to fill this string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);


        btnEditPackages = findViewById(R.id.btnEditPackages);
        btnEditBookings = findViewById(R.id.btnEditBookings);
        btnEditCustomers = findViewById(R.id.btnEditCustomers);
        btnEmpLogout = findViewById(R.id.btnEmpLogout);
        tvEmpWelcome = findViewById(R.id.tvEmpWelcome);

        tvEmpWelcome.setText("Welcome" + username + "!");



        btnEditPackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomeActivity.this, EditPackagesActivity.class));
            }
        });

        btnEditBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeHomeActivity.this, EditBookingsActivity.class));
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