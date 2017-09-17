package com.example.android.musicstructureapp.Song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.musicstructureapp.PlayNowActivity;
import com.example.android.musicstructureapp.Playlist.Playlist;
import com.example.android.musicstructureapp.R;

import java.util.ArrayList;


public class SongFragment extends Fragment {
    ArrayAdapter<Song> mAdapter;

    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_song, container, false);
        final ArrayList<Song> items = new ArrayList<>();
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1));
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2));
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1));
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2));
        Playlist playlist1 = new Playlist(getString(R.string.playlist_1), new Song[]{song3, song4});
        Playlist playlist2 = new Playlist(getString(R.string.playlist_2), new Song[]{song2, song4});
        song2.setPlaylist(new Playlist[]{playlist2});
        song3.setPlaylist(new Playlist[]{playlist1});
        song4.setPlaylist(new Playlist[]{playlist1, playlist2});
        items.add(song1);
        items.add(song2);
        items.add(song3);
        items.add(song4);

        ListView listView =rootView.findViewById(R.id.listViewSong);

        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(getActivity(),PlayNowActivity.class);
                                                intent.putExtra("songName_Song", items.get(position).getSongName());
                                                intent.putExtra("albumName_Song",items.get(position).getSongAlbum());
                                                intent.putExtra("artistName_Song",items.get(position).getSongArtist());
                                                intent.putExtra("key", "Song");
                                                startActivity(intent);
                                            }
                                        }
        );
        return rootView;
    }
}
