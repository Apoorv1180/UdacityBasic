package com.example.android.musicstructureapp.Artist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructureapp.Album.Album;
import com.example.android.musicstructureapp.PlayNowActivity;
import com.example.android.musicstructureapp.R;
import com.example.android.musicstructureapp.Song.Song;

import java.util.ArrayList;

public class ArtistAlbumInfo extends AppCompatActivity {
    ArrayAdapter<Song> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_album);

        String albumName = getIntent().getStringExtra("album");
        getSupportActionBar().setTitle(albumName);

        final ArrayList<Song> items = new ArrayList<Song>();
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1));
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2));
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1));
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2));
        Album album1 = new Album(getString(R.string.album_1), new Song[]{song1, song3});
        Album album2 = new Album(getString(R.string.album_2), new Song[]{song2, song4});
        if (albumName.equals(album1.getAlbumName())) {
            Song[] songs = album1.getSongs();
            for (Song s : songs) {
                items.add(s);
            }
        }
        if (albumName.equals(album2.getAlbumName())) {
            Song[] songs = album2.getSongs();
            for (Song s : songs) {
                items.add(s);
            }
        }

        ListView listView = (ListView) findViewById(R.id.listView);

        mAdapter = new ArrayAdapter<Song>(ArtistAlbumInfo.this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(ArtistAlbumInfo.this, PlayNowActivity.class);
                                                intent.putExtra("songName_Artist", items.get(position).getSongName());
                                                intent.putExtra("albumName_Artist", items.get(position).getSongAlbum());
                                                intent.putExtra("artistName_Artist", items.get(position).getSongArtist());
                                                intent.putExtra("key", "Artist");
                                                startActivity(intent);
                                            }
                                        }
        );
    }
}
