package com.example.android.projectsixdemo.hotel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class HotelActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Hotel> list = new ArrayList<Hotel>();
    int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    String[] hotelNames, hotelRatings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        hotelNames = getResources().getStringArray(R.array.hotelNames);
        hotelRatings = getResources().getStringArray(R.array.hotelRatings);
        int count = 0;
        for (String Name : hotelNames) {
            Hotel info = new Hotel(imageId[count], Name, hotelRatings[count]);
            count++;
            list.add(info);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHotel);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new HotelAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
