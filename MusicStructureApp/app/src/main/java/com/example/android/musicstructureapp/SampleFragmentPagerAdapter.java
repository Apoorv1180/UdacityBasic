package com.example.android.musicstructureapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.musicstructureapp.Album.AlbumFragment;
import com.example.android.musicstructureapp.Artist.ArtistFragment;
import com.example.android.musicstructureapp.Playlist.PlaylistFragment;
import com.example.android.musicstructureapp.Song.SongFragment;


public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 5;
    //   private String tabTitles[] = new String[] { "Home", "Albums", "Artists","Songs" };
    private Context mContext;

    public SampleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.HomeFragmentTitle);
        } else if (position == 1) {
            return mContext.getString(R.string.artistFragmentTitle);
        } else if (position == 2) {
            return mContext.getString(R.string.PlaylistFragmentTitle);
        } else if (position == 3) {
            return mContext.getString(R.string.songFragmentTitle);
        } else {
            return mContext.getString(R.string.albumFragmentTitle);
        }
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new ArtistFragment();
        } else if (position == 2) {
            return new PlaylistFragment();
        } else if (position == 3) {
            return new SongFragment();
        } else {
            return new AlbumFragment();
        }
    }

}