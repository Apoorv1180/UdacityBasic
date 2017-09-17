package com.example.android.musicstructureapp.Playlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.musicstructureapp.R;
import com.example.android.musicstructureapp.Song.Song;

import java.util.ArrayList;


public class PlaylistFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Playlist> items = new ArrayList<Playlist>();
    public PlaylistFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);

        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1));
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2));
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1));
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2));
        Playlist playlist1 = new Playlist(getString(R.string.playlist_1), new Song[]{song3, song4,song1});
        Playlist playlist2 = new Playlist(getString(R.string.playlist_2), new Song[]{song2, song4});
        song2.setPlaylist(new Playlist[]{playlist2});
        song3.setPlaylist(new Playlist[]{playlist1});
        song4.setPlaylist(new Playlist[]{playlist1, playlist2});
        items.add(playlist1);
        items.add(playlist2);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlaylistAdapter(items, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}