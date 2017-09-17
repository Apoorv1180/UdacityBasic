package com.example.android.musicstructureapp.Song;


import com.example.android.musicstructureapp.Playlist.Playlist;

//Creating utility class Song
public class Song {

    private String mSongName;
    private String mSongArtist;
    private String mSongAlbum;
    private Playlist[] mPlaylist;
//Constructors
    public Song(String name, String artist, String album) {
        this.mSongName = name;
        this.mSongArtist = artist;
        this.mSongAlbum = album;
    }

    public Song(String name, String artist, String album, Playlist[] playlist) {
        this.mSongName = name;
        this.mSongArtist = artist;
        this.mSongAlbum = album;
        this.mPlaylist = playlist;
    }
    //geters
    public String getSongName() {
        return mSongName;
    }
    public String getSongArtist() {
        return mSongArtist;
    }
    public String getSongAlbum() {
        return mSongAlbum;
    }
    public Playlist[] getPlaylist() {
        return mPlaylist;
    }
    public void setPlaylist(Playlist[] playlist) {
        mPlaylist = playlist;
    }

    @Override
    public String toString() {
        return mSongName;
    }
}

