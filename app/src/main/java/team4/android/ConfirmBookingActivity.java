package team4.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfirmBookingActivity extends AppCompatActivity {

    EditText etPackageName, etPackageStartDate, etPackageEndDate, etPackagePrice;
    Button btnBack, btnBookPackage;
    EditText mlPackageDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);

        btnBack = findViewById(R.id.btnBack);
        btnBookPackage = findViewById(R.id.btnBookPackage);

        etPackageName = findViewById(R.id.etPackageName);
        etPackageName.setEnabled(false);

        etPackageStartDate = findViewById(R.id.etPackageStartDate);
        etPackageStartDate.setEnabled(false);

        etPackageEndDate = findViewById(R.id.etPackageEndDate);
        etPackageEndDate.setEnabled(false);

        mlPackageDesc = findViewById(R.id.mlPackageDesc);
        mlPackageDesc.setEnabled(false);


//        etPackageDesc = findViewById(R.id.etPackageDesc);
//        etPackageDesc.setEnabled(false);

        etPackagePrice = findViewById(R.id.etPackagePrice);
        etPackagePrice.setEnabled(false);

        Intent intent = getIntent();
        Package pkg = (Package) intent.getSerializableExtra("package");

        etPackageName.setText(pkg.getPkgName());

        etPackageStartDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(pkg.getPkgStartDate()));
        etPackageEndDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(pkg.getPkgEndDate()));
        mlPackageDesc.setText(pkg.getPkgDesc());
        etPackagePrice.setText("$" + pkg.getPkgBasePrice());





        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }


        });

        btnBookPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Fill-in SQL here

                 */

                startActivity(new Intent(ConfirmBookingActivity.this, BookingConfirmationActivity.class));
            }
        });


    }
}