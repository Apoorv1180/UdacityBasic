package com.example.android.musicstructureapp.Album;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructureapp.PlayNowActivity;
import com.example.android.musicstructureapp.R;

import java.util.ArrayList;

public class AlbumInfo extends AppCompatActivity {
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        final ArrayList<String> items = new ArrayList<String>();
        //fetching details from intent provided by the albumAdapter onclick
        final String[] songsName = getIntent().getStringArrayExtra("songList");
        final String albumName = getIntent().getStringExtra("albumName");
        final String artistName = getIntent().getStringExtra("artistName");
        //setting title for the activity
        getSupportActionBar().setTitle(albumName);
        //adding items for listview
        for (int i = 0; i < songsName.length; i++) {
            items.add(songsName[i]);
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(AlbumInfo.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(AlbumInfo.this, PlayNowActivity.class);
                                                intent.putExtra("songName_Album", songsName[position]);
                                                intent.putExtra("albumName_Album", albumName);
                                                intent.putExtra("artistName_Album", artistName);
                                                intent.putExtra("key", "Album");
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}
