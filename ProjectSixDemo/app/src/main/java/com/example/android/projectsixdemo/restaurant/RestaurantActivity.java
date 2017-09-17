package com.example.android.projectsixdemo.restaurant;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Restaurant> list = new ArrayList<Restaurant>();
    int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    String[] restaurantName, restaurantRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        restaurantName = getResources().getStringArray(R.array.restaurant);
        restaurantRating = getResources().getStringArray(R.array.restaurantRatings);
        int count = 0;

        for (String Name : restaurantName) {
            Restaurant info = new Restaurant(imageId[count], Name, restaurantRating[count]);
            count++;
            list.add(info);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHotel);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RestaurantAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
