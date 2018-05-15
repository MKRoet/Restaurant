package com.example.gebruiker.restaurant;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private final ArrayList<MenuItem> menuItems;
    private final Context c;


    // Constructor.
    public MenuItemAdapter(@NonNull Context context, @NonNull ArrayList<MenuItem> menuList) {
        super(context, R.layout.activity_menu_row, menuList);

        menuItems = menuList;
        c = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_row, parent, false);
        }

        // Get views.
        TextView name = view.findViewById(R.id.menu_name);
        TextView price = view.findViewById(R.id.menu_price);
        ImageView image = view.findViewById(R.id.menu_image);

        MenuItem item = menuItems.get(position);
        String url = item.getImageURL();

        // Set values.
        name.setText(item.getName());
        price.setText(item.getPrice());
        Glide.with(this.getContext()).load(url).into(image);

        return view;

        }
}



