package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: Home page for Customers. Provides access to all other areas for customers.
 ***/
public class HomeActivity extends AppCompatActivity {

    Button btnPackages, btnContact, btnBookings, btnLogout;
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvEmpWelcome);

        btnPackages = findViewById(R.id.btnPackages);
        btnContact = findViewById(R.id.btnContact);
        btnBookings = findViewById(R.id.btnBookings);
        btnLogout = findViewById(R.id.btnLogout);

        String username = "";                    // This was meant to gather user information from login activity and display it in the header which was abandoned due to time constraint.
        tvWelcome.setText("Welcome" + username + "!");

        btnPackages.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(HomeActivity.this, BookPackageActivity.class));
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ContactActivity.class));
            }
        });

        btnBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, BookingsActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WelcomeActivity.class));
            }
        });


    }
}