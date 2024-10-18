package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText areaEditText;
    private EditText pinCodeEditText;
    private EditText buildingNameEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize views
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        phoneEditText = findViewById(R.id.phoneNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        areaEditText = findViewById(R.id.areaEditText);
        pinCodeEditText = findViewById(R.id.pinCodeEditText);
        buildingNameEditText = findViewById(R.id.buildingNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);

        DBHandler dbHelper = new DBHandler(this);
        db = dbHelper.getWritableDatabase();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString().trim();
                String lastName = lastNameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                String area = areaEditText.getText().toString().trim();
                String pinCode = pinCodeEditText.getText().toString().trim();
                String buildingName = buildingNameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Insert user details into the database
                ContentValues values = new ContentValues();
                values.put(DBHandler.UserDetails.COLUMN_FIRST_NAME, firstName);
                values.put(DBHandler.UserDetails.COLUMN_LAST_NAME, lastName);
                values.put(DBHandler.UserDetails.COLUMN_PHONE, phone);
                values.put(DBHandler.UserDetails.COLUMN_EMAIL, email);
                values.put(DBHandler.UserDetails.COLUMN_AREA, area);
                values.put(DBHandler.UserDetails.COLUMN_PIN_CODE, pinCode);
                values.put(DBHandler.UserDetails.COLUMN_BUILDING_NAME, buildingName);
                values.put(DBHandler.UserDetails.COLUMN_PASSWORD, password);
                db.insert(DBHandler.UserDetails.TABLE_NAME, null, values);

                Toast.makeText(SignUpActivity.this, "User details saved", Toast.LENGTH_SHORT).show();

                // Navigate to the user dashboard activity
                Intent intent = new Intent(SignUpActivity.this, UserDashboardActivity.class);
                startActivity(intent);
            }

        });
    }
}