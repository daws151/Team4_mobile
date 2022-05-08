package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*** Author: William Rust, Harbir Singh, Brett Dawson
 *   Date: May 07, 2022
 *   Comments: This is the start point of the application, it gives access to the Customer or Employee pages.
 ***/
public class WelcomeActivity extends AppCompatActivity {

    Button btnEmployees, btnHOMETEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnEmployees = findViewById(R.id.btnEmployees);
        btnHOMETEMP = findViewById(R.id.btnHOMETEMP);

        btnEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, EmployeeHomeActivity.class));
            }
        });

        btnHOMETEMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            }
        });
    }
}