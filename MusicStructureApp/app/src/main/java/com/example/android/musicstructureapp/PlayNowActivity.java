package com.example.android.musicstructureapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PlayNowActivity extends AppCompatActivity {

    TextView displaySongInfo;
    String key_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_now);
        getSupportActionBar().setTitle("Play Now");
        displaySongInfo = (TextView) findViewById(R.id.displaySongInfo);
        key_activity = getIntent().getStringExtra("key");

        if (key_activity.equals("Album")) {
            String songName = getIntent().getStringExtra("songName_Album");
            String albumName = getIntent().getStringExtra("albumName_Album");
            String artistName = getIntent().getStringExtra("artistName_Album");

            displaySongInfo.setText(songName + " of " + albumName + " by " + artistName);
        } else if (key_activity.equals("Artist")) {
            String songName = getIntent().getStringExtra("songName_Artist");
            String albumName = getIntent().getStringExtra("albumName_Artist");
            String artistName = getIntent().getStringExtra("artistName_Artist");

            displaySongInfo.setText(songName + " of " + albumName + " by " + artistName);

        } else if (key_activity.equals("Playlist")) {

            String songName = getIntent().getStringExtra("songName_Playlist");
            String playlistName = getIntent().getStringExtra("playlistName_Playlist");

            displaySongInfo.setText(songName + " of " + playlistName);

        } else if (key_activity.equals("Song")) {

            String songName = getIntent().getStringExtra("songName_Song");
            String albumName = getIntent().getStringExtra("albumName_Song");
            String artistName = getIntent().getStringExtra("artistName_Song");

            displaySongInfo.setText(songName + " of " + albumName + " by " + artistName);

        }

    }
}

