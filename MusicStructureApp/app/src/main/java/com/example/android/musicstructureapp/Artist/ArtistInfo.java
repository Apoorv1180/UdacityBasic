package com.example.android.musicstructureapp.Artist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructureapp.R;

import java.util.ArrayList;

public class ArtistInfo extends AppCompatActivity {
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        final ArrayList<String> items = new ArrayList<String>();
        final String[] albumName = getIntent().getStringArrayExtra("albumList");
        final String artistName = getIntent().getStringExtra("artistName");
        getSupportActionBar().setTitle(artistName);
        for (int i = 0; i < albumName.length; i++) {
            items.add(albumName[i]);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(ArtistInfo.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(ArtistInfo.this, ArtistAlbumInfo.class);
                                                intent.putExtra("album", albumName[position]);
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}
