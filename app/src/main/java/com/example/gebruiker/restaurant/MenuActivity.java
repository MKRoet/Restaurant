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

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String category = bundle.getString("category");

        MenuItemsRequest request = new MenuItemsRequest(this);
        request.getMenuItems(this, category);

        // Set onitem click lisener.
        ListView listView = findViewById(R.id.itemList);
        AdapterView.OnItemClickListener clicked = new ItemClickListener();
        listView.setOnItemClickListener(clicked);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> items) {

        ListView listview = findViewById(R.id.itemList);
        MenuItemAdapter adapter = new MenuItemAdapter(this, items);

        listview.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError (String message) {

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int i, long l) {

            // Get string with category name.
            MenuItem menuItem = (MenuItem) adapter.getItemAtPosition(i);

            String name = menuItem.getName();
            String url = menuItem.getImageURL();
            String price = menuItem.getPrice();
            String description = menuItem.getDescription();

            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            bundle.putString("url", url);
            bundle.putString("price", price);
            bundle.putString("description", description);

            // Start next activity with appropiate data when clicked.
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("bundle", bundle);
            startActivity(intent);
        }
    }
}
