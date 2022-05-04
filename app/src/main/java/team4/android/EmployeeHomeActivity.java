package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_home);

        Button btnEditPackages = findViewById(R.id.btnEditPackages);
        Button btnEditBookings = findViewById(R.id.btnEditBookings);
        Button btnEditCustomers = findViewById(R.id.btnEditCustomers);


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

    }
}