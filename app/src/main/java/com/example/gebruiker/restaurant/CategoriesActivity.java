package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // Set onitem click lisener.
        ListView listView = findViewById(R.id.RestoList);
        AdapterView.OnItemClickListener clicked = new ListItemClickListener();
        listView.setOnItemClickListener(clicked);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        ListView listview = findViewById(R.id.RestoList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item,
                R.id.text1, categories);
        listview.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {

            // Get string with category name.
            String category = (String) adapter.getItemAtPosition(i);

            Bundle bundle = new Bundle();
            bundle.putString("category", category);

            // Start next activity with appropiate data when clicked.
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("bundle", bundle);
            startActivity(intent);

        }
    }
}
