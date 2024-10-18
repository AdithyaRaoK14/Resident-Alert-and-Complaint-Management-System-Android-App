package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserDashboardActivity extends AppCompatActivity {

    FloatingActionButton mAddFab;
    ListView notif_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        notif_list = findViewById(R.id.notif_list);

        showNotifications();

        mAddFab = findViewById(R.id.new_query);

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDashboardActivity.this, user_query.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.water:
                fetchCategorywiseNotifications("Water");
                Toast.makeText(UserDashboardActivity.this, "Loading water notifications...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.electricity:
                fetchCategorywiseNotifications("Electricity");
                Toast.makeText(UserDashboardActivity.this, "Loading electricity notifications...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.traffic:
                fetchCategorywiseNotifications("Traffic");
                Toast.makeText(UserDashboardActivity.this, "Loading traffic notifications...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.road_work:
                fetchCategorywiseNotifications("Road_work");
                Toast.makeText(UserDashboardActivity.this, "Loading road_work notifications...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.waste:
                fetchCategorywiseNotifications("Waste");
                Toast.makeText(UserDashboardActivity.this, "Loading waste notifications...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fetchCategorywiseNotifications(String category){
        DBHelper DB = new DBHelper(this);
        ArrayList<String> notifList = DB.categoryWiseNotification(category);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notifList);
        notif_list.setAdapter(adapter);
    }

    public void showNotifications(){
        DBHelper DB = new DBHelper(this);
        ArrayList<String> namesList = DB.getAllNotifications();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, namesList);

        notif_list.setAdapter(adapter);
    }
}