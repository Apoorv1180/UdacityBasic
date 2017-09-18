package com.example.android.projectsixdemo.home;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<homeCardViewInfo> list = new ArrayList<homeCardViewInfo>();
    int[] imageId = {R.mipmap.ic_how_to_reach, R.mipmap.ic_hotel, R.mipmap.ic_restaurant, R.mipmap.ic_placestovisit, R.mipmap.ic_nightlife, R.mipmap.ic_map};
    String[] homeCardViewOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolBar);
        collapsingToolbarLayout.setTitle(getString(R.string.Pune));
        homeCardViewOptions = getResources().getStringArray(R.array.cardMenu);
        int count = 0;
        for (String Name : homeCardViewOptions) {
            homeCardViewInfo info = new homeCardViewInfo(imageId[count], Name);
            count++;
            list.add(info);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new homeCardViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}
