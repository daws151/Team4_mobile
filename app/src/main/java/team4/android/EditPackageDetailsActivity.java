package team4.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
/*** Author: William Rust
 *   Date: May 07, 2022
 *   Comments: This page displays the chosen Package in greater detail. Database editing functions not implemented.
 ***/
public class EditPackageDetailsActivity extends AppCompatActivity {


    EditText etPackageName, etPackageStartDate, etPackageEndDate, mlPackageDesc, etPackagePrice, etPackageCommission;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_package_details);

        etPackageName = findViewById(R.id.etPackageName);
        etPackageStartDate = findViewById(R.id.etPackageStartDate);
        etPackageEndDate = findViewById(R.id.etPackageEndDate);
        mlPackageDesc = findViewById(R.id.mlPackageDesc);
        etPackagePrice = findViewById(R.id.etPackagePrice);
        etPackageCommission = findViewById(R.id.etPackageCommission);

        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Package pkg = (Package) intent.getSerializableExtra("package");

        etPackageName.setText(pkg.getPkgName());
        etPackageStartDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(pkg.getPkgStartDate()));
        etPackageEndDate.setText(new SimpleDateFormat("yyyy-MMM-dd").format(pkg.getPkgEndDate()));
        mlPackageDesc.setText(pkg.getPkgDesc());
        etPackagePrice.setText("$" + pkg.getPkgBasePrice());
        etPackageCommission.setText("$" + pkg.getPkgAgencyCommission());


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}