package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    private Button adminLoginButton;
    private EditText adminUsernameEditText, adminPasswordEditText;

//    DBHandler DB;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

         DB = new DBHelper(this);

        adminLoginButton = (Button) findViewById(R.id.adminLoginButton);
        adminUsernameEditText = (EditText) findViewById(R.id.adminUsernameEditText);
        adminPasswordEditText = (EditText) findViewById(R.id.adminPasswordEditText);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String area_pin = adminUsernameEditText.getText().toString();
                String pass = adminPasswordEditText.getText().toString();

                if(area_pin.equals("")||pass.equals(""))
                    Toast.makeText(AdminLoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(area_pin, pass);
                    if(checkuserpass==true){
                        Toast.makeText(AdminLoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AdminLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

//                Intent intent = new Intent(AdminLoginActivity.this, AdminDashboardActivity.class);
//                startActivity(intent);
            }
        });
    }
    public void back(View view){
        Intent intent = new Intent(AdminLoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}