package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This is a generic confirmation page. Database modification was not completed, so this does nothing other than return the user to the home page.
 ***/
public class BookingConfirmationActivity extends AppCompatActivity {

    TextView tvConfirmation;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);

        tvConfirmation = findViewById(R.id.tvConfirmation);
        btnHome = findViewById(R.id.btnHome);

        // helps format the text using HTML, allowing use of tags to underline key spans
        tvConfirmation.setText(Html.fromHtml("Your package has been booked! You can view all bookings from the <u>Home</u> page by clicking  <u>My Bookings</u>."));

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookingConfirmationActivity.this, HomeActivity.class));
            }
        });

    }
}