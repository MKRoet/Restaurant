package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Callback activity;
    private Context context;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // Constructor.
    public CategoriesRequest(Context contextParamater) {
        this.context = contextParamater;
    }

    //
    public void getCategories(Callback activity) {

        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        activity.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList<String> categories = new ArrayList<>();

        try {
            JSONArray c = response.getJSONArray("categories");
            Log.d("jarray",c.toString());

            for (int i = 0; i < c.length(); i++) {
                categories.add(c.getString(i));
                Log.d("Response", c.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        activity.gotCategories(categories);

    }


}
