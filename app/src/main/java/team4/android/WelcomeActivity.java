package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team4.android.ui.login.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {


    Button btnLogin, btnEmployees, btnHOMETEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        btnLogin = findViewById(R.id.btnLogin);
        btnEmployees = findViewById(R.id.btnEmployees);
        btnHOMETEMP = findViewById(R.id.btnHOMETEMP);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//            }
//        });

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