package com.example.android.musicstructureapp.Playlist;

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

public class PlaylistInfo extends AppCompatActivity {

    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);
        final ArrayList<String> items = new ArrayList<String>();
        final String playlistName = getIntent().getStringExtra("playListName");
        final String[] songName = getIntent().getStringArrayExtra("songList");
        getSupportActionBar().setTitle(playlistName);

        for (int i = 0; i < songName.length; i++) {
            items.add(songName[i]);
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(PlaylistInfo.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(PlaylistInfo.this, PlayNowActivity.class);
                                                intent.putExtra("songName_Playlist", songName[position]);
                                                intent.putExtra("playlistName_Playlist",playlistName);
                                                intent.putExtra("key","Playlist");
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}
