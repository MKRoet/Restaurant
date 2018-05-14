package com.example.gebruiker.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public void getMenuItems(ArrayList<MenuItem> categories) {

        ListView listview = findViewById(R.id.RestoList);
        ArrayAdapter<MenuItem> adapter = new ArrayAdapter<>(this, R.layout.list_item,
                categories);

        listview.setAdapter(adapter);
    }

}
