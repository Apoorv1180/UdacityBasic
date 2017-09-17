package com.example.android.musicstructureapp.Album;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.musicstructureapp.R;
import com.example.android.musicstructureapp.Song.Song;

import java.util.ArrayList;


public class AlbumFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Album> list = new ArrayList<Album>();

    public AlbumFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);
        int id = R.mipmap.ic_launcher;
        //Since we are not using any database
        //hence locally initialising the list items.

        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1));
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_1), getString(R.string.album_1));
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1));
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_1), getString(R.string.album_1));
        Album album1 = new Album(id, getString(R.string.album_1), getString(R.string.artist_1), new Song[]{song1, song3, song4, song2});
        Album album2 = new Album(id, getString(R.string.album_2), getString(R.string.artist_2), new Song[]{song2, song4});
        Album album3 = new Album(id, getString(R.string.album_1), getString(R.string.artist_1), new Song[]{song1, song3});
        Album album4 = new Album(id, getString(R.string.album_2), getString(R.string.artist_1), new Song[]{song2, song4});
        Album album5 = new Album(id, getString(R.string.album_1), getString(R.string.artist_1), new Song[]{song1, song3});
        Album album6 = new Album(id, getString(R.string.album_2), getString(R.string.artist_1), new Song[]{song2, song4});
        Album album7 = new Album(id, getString(R.string.album_1), getString(R.string.artist_1), new Song[]{song1, song3});
        Album album8 = new Album(id, getString(R.string.album_2), getString(R.string.artist_1), new Song[]{song2, song4});

        list.add(album1);
        list.add(album2);
        list.add(album3);
        list.add(album4);
        list.add(album5);
        list.add(album6);
        list.add(album7);
        list.add(album8);

        //using Album adapter to inflate the card view to the recycler view
        recyclerView = rootView.findViewById(R.id.recyclerView);
        int numberOfColumns = 2;
        layoutManager = new GridLayoutManager(getActivity(), numberOfColumns);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AlbumAdapter(list, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}