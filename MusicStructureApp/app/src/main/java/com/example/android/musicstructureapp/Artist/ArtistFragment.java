package com.example.android.musicstructureapp.Artist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.musicstructureapp.Album.Album;
import com.example.android.musicstructureapp.R;
import com.example.android.musicstructureapp.Song.Song;

import java.util.ArrayList;

import static android.R.attr.id;


public class ArtistFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Artist> items = new ArrayList<Artist>();

    public ArtistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);
        //locally intialising since so database or filefolder used to extract info.
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1));
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2));
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1));
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2));
        Album album1 = new Album(id, getString(R.string.album_1), getString(R.string.artist_1), new Song[]{song1, song3});
        Album album2 = new Album(id, getString(R.string.album_2), getString(R.string.artist_2), new Song[]{song2, song4});
        Artist artist1 = new Artist(getString(R.string.artist_1), new Song[]{song1, song2, song3}, new Album[]{album1});
        Artist artist2 = new Artist(getString(R.string.artist_2), new Song[]{song3, song4}, new Album[]{album2});
        items.add(artist1);
        items.add(artist2);


        recyclerView = rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArtistAdapter(items, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}