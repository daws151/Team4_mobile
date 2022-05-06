package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        String username = "";                                         // If possible, gather the Customer's first name to fill this string

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