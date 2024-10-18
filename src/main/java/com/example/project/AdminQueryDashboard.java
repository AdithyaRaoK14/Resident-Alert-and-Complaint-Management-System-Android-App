package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminQueryDashboard extends AppCompatActivity {

    private ListView admin_query_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_query_dashboard);

        admin_query_list = (ListView) findViewById(R.id.admin_query_list);

        showQueries();
    }

    public void showQueries(){
        DBHelper DB = new DBHelper(this);
        ArrayList<String> queryList = DB.fetchQueries("576104");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, queryList);

        admin_query_list.setAdapter(adapter);
    }
}