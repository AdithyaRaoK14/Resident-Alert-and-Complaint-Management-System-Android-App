package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class user_query extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText pinCodeEditText;
    private EditText queryEditText;
    private Spinner categorySpinner;
    private Button submitButton;
    private int ID = 2;

    String category;

    String[] categories = { "Water", "Electricity",
            "Traffic", "Road Work",
            "Waste Management"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_query);

        pinCodeEditText = findViewById(R.id.pinCodeEditText);
        queryEditText = findViewById(R.id.queryEditText);
        categorySpinner = findViewById(R.id.categorySpinner);
        submitButton = findViewById(R.id.submitButton);



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

        // Handle submit button click
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String pinCode = pinCodeEditText.getText().toString();
                String query = queryEditText.getText().toString();
                String selected_category = category;

                // Validate input
                if (pinCode.isEmpty() || query.isEmpty() || selected_category.isEmpty()) {
                    Toast.makeText(user_query.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Save query to database
                DBHandler dbHelper = new DBHandler(user_query.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DBHandler.UserQuery.COLUMN_PIN_CODE, pinCode);
                values.put(DBHandler.UserQuery.COLUMN_QUERY, query);
                values.put(DBHandler.UserQuery.COLUMN_CATEGORY, selected_category);
//                values.put(DBHandler.UserQuery.COLUMN_ID, ID++);

                long newRowId = db.insert(DBHandler.UserQuery.TABLE_NAME, null, values);

                if (newRowId == -1) {
                    Toast.makeText(user_query.this, "Error saving query to database", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Show success message
                Toast.makeText(user_query.this, "Query submitted successfully", Toast.LENGTH_SHORT).show();
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