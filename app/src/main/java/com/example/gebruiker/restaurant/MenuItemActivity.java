package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Get intent and bundle.
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        // Get data.
        String name = bundle.getString("name");
        String url = bundle.getString("url");
        String price = bundle.getString("price");
        String description = bundle.getString("description");

        // Get views.
        TextView titleDetail = findViewById(R.id.titleDetail);
        ImageView imageDetail = findViewById(R.id.imageDetail);
        TextView descriptionDetail = findViewById(R.id.descriptionDetail);
        TextView priceDetail = findViewById(R.id.priceDetail);

        // Filling xml field with appropiate data.
        titleDetail.setText(name);
        Glide.with(this).load(url).into(imageDetail);
        descriptionDetail.setText(description);
        priceDetail.setText(price);

    }
}
