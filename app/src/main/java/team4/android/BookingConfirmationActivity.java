package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingConfirmationActivity extends AppCompatActivity {

    TextView tvConfirmation;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        tvConfirmation = findViewById(R.id.tvConfirmation);
        btnHome = findViewById(R.id.btnHome);

        tvConfirmation.setText(Html.fromHtml("Your package has been booked! You can view all bookings from the <u>Home</u> page by clicking  <u>My Bookings</u>."));

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingConfirmationActivity.this, HomeActivity.class));
            }
        });

    }
}