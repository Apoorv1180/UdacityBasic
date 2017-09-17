package com.example.android.musicstructureapp.Playlist;


import com.example.android.musicstructureapp.Song.Song;

//Creating util class Playlist
public class Playlist {
    private String mPlaylistName;
    private Song[] mSongs;

    //Constructor
    public Playlist(String playlistName, Song[] songs) {
        mPlaylistName = playlistName;
        mSongs = songs;
    }

    //getters
    public String getPlaylistName() {
        return mPlaylistName;
    }

    public String[] getSongNames() {
        int len = mSongs.length;
        String[] songNames = new String[len];
        for (int i = 0; i < len; i++) {
            songNames[i] = mSongs[i].getSongName();
        }
        return songNames;
    }

    @Override
    public String toString() {
        return mPlaylistName;
    }
}
