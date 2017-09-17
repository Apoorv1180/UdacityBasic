package com.example.android.musicstructureapp.Artist;

import com.example.android.musicstructureapp.Album.Album;
import com.example.android.musicstructureapp.Song.Song;

//Creating utility class Artist
public class Artist {
    private String mArtistName;
    private Song[] mSongs;
    private Album[] mAlbums;

    //Constructor
    public Artist(String artistName, Song[] songs, Album[] albums) {
        mArtistName = artistName;
        mSongs = songs;
        mAlbums = albums;
    }
//getters

    public String getArtistName() {
        return mArtistName;
    }

    public String[] getSongNames() {
        int len = mSongs.length;
        String[] songNames = new String[len];
        for (int i = 0; i < len; i++) {
            songNames[i] = mSongs[i].getSongName();
        }
        return songNames;
    }

    public String[] getAlbumNames() {
        int len = mAlbums.length;
        String[] albumNames = new String[len];
        for (int i = 0; i < len; i++) {
            albumNames[i] = mAlbums[i].getAlbumName();
        }
        return albumNames;
    }

    @Override
    public String toString() {
        return mArtistName;
    }
}
