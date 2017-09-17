package com.example.android.projectsixdemo.nightlife;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class NightLifeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<NightLife> list = new ArrayList<NightLife>();
    int[] imageId = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    String[] nightlifeName, nightlifeRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        nightlifeName = getResources().getStringArray(R.array.nightlifeNames);
        nightlifeRating = getResources().getStringArray(R.array.nightlifeRatings);
        int count = 0;

        for (String Name : nightlifeName) {
            NightLife info = new NightLife(imageId[count], Name, nightlifeRating[count]);
            count++;
            list.add(info);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRestaurant);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new NightlifeAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
