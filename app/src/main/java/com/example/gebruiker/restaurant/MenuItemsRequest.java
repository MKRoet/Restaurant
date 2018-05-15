package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Callback activity;
    Context context;
    String menuCategory;

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> arrayList);
        void gotMenuItemsError(String message);
    }

    // Constructor.
    public MenuItemsRequest(Context context) {

        this.context = context;
    }

    public void getMenuItems(Callback callback, String category) {

        activity = callback;
        menuCategory = category;

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu?category" + category;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        activity.gotMenuItemsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList<MenuItem> arrayList = new ArrayList<>();

        try {
            JSONArray items = response.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject jsonObject = items.getJSONObject(i);

                String category = jsonObject.getString("category");
                String description = jsonObject.getString("description");
                String price = jsonObject.getString("price");
                String image_url = jsonObject.getString("image_url") ;
                String name = jsonObject.getString("name");

                MenuItem menuItem = new MenuItem(name, description, image_url, price, category);
                String dishCategory = menuItem.getCategory();

                Log.d("dishcat: ", dishCategory);
                Log.d("menucat: ", "is: " + menuCategory);

                if (dishCategory.equals(menuCategory)) {
                    arrayList.add(menuItem);
                }
            }
            activity.gotMenuItems(arrayList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}