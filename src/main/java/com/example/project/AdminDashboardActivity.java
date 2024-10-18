package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AdminDashboardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText adminNoticeTitle1, adminNoticePincode1, adminNoticeDetails1, adminNoticeCategory1;
    private Button adminNoticeSubmitButton1, adminFetchQueriesButton;

    private Spinner categorySpinner;

    String category;

    String[] categories = { "Water", "Electricity",
            "Traffic", "Road Work",
            "Waste Management"};

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        DB = new DBHelper(this);

        adminNoticeTitle1 = (EditText) findViewById(R.id.adminNoticeTitle);
        adminNoticePincode1 = (EditText) findViewById(R.id.adminNoticePincode);
        adminNoticeDetails1 = (EditText) findViewById(R.id.adminNoticeDetails);
//        adminNoticeCategory1 = (EditText) findViewById(R.id.adminNoticeCategory);
        categorySpinner = findViewById(R.id.categorySpinner);
        adminNoticeSubmitButton1 = (Button) findViewById(R.id.adminNoticeSubmitButton);
        adminFetchQueriesButton = (Button) findViewById(R.id.adminFetchQueriesButton);

        Spinner spin = findViewById(R.id.categorySpinner);
        spin.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categories);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spin.setAdapter(ad);


        adminNoticeSubmitButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin, title, details, categoryTemp;
                pin = adminNoticePincode1.getText().toString();
                title = adminNoticeDetails1.getText().toString();
                details = adminNoticeDetails1.getText().toString();
//                category = adminNoticeCategory1.getText().toString();
                categoryTemp = category;

//                if(pin.equals("576104")){
//                    Toast.makeText(AdminDashboardActivity.this, "The notification will be sent", Toast.LENGTH_SHORT).show();
//                } else if(pin == "hello" || title == "test"){
//                    Toast.makeText(AdminDashboardActivity.this, "The aksdfjl;asdkfj notification will be sent", Toast.LENGTH_SHORT).show();
//                }
                Toast.makeText(AdminDashboardActivity.this, "The notification will be sent", Toast.LENGTH_SHORT).show();
                DB.insertNotification(pin, title, details, 1, categoryTemp);
            }
        });

        adminFetchQueriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboardActivity.this, AdminQueryDashboard.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        category = categories[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}