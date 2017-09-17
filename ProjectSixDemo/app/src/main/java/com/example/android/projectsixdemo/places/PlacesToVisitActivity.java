package com.example.android.projectsixdemo.places;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class PlacesToVisitActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<PlacesToVisit> list = new ArrayList<PlacesToVisit>();
    int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    String[] placeName, placeRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_visit);
        placeName = getResources().getStringArray(R.array.placeNames);
        placeRating = getResources().getStringArray(R.array.placeRatings);
        int count = 0;

        for (String Name : placeName) {
            PlacesToVisit info = new PlacesToVisit(imageId[count], Name, placeRating[count]);
            count++;
            list.add(info);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlacesToVisit);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new PlacesAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}