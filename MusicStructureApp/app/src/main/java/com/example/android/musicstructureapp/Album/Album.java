package com.example.android.musicstructureapp.Album;


import com.example.android.musicstructureapp.Song.Song;


//Creating utility class Album

public class Album {
    private int mImageId;
    private String mAlbumName;
    private String mArtistName;
    private Song[] mSongs;

    //Constructor
    public Album(int imageId, String albumName, String artistName, Song[] songs) {
        this.mImageId = imageId;
        this.mAlbumName = albumName;
        this.mArtistName = artistName;
        this.mSongs = songs;
    }

    //Constructor
    public Album(String album, Song[] songs) {
        this.mAlbumName = album;
        this.mSongs = songs;
    }

//Declaring getters

    public int getImageId() {
        return mImageId;
    }

    public Song[] getSongs() {
        return mSongs;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String[] getSongNames() {
        int lengthOfSongArray = mSongs.length;
        String[] songNamesArray = new String[lengthOfSongArray];
        for (int i = 0; i < lengthOfSongArray; i++) {
            songNamesArray[i] = mSongs[i].getSongName();
        }
        return songNamesArray;
    }


}
