package com.example.android.musicstructureapp.Album;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicstructureapp.R;

import java.util.ArrayList;


//Album Adapter class

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {
    public ArrayList<Album> mAlbum = new ArrayList<Album>();
    Context ctx;

    public AlbumAdapter(ArrayList<Album> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album, parent, false);
        AlbumHolder albumHolder = new AlbumHolder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(AlbumHolder holder, int position) {
        Album album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.artistName.setText(album.getArtistName());
        holder.albumName.setText(album.getAlbumName());
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }

    public static class AlbumHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView albumImageView;
        TextView albumName;
        TextView artistName;
        ArrayList<Album> album = new ArrayList<Album>();
        Context ctx;

        public AlbumHolder(View itemView, Context ctx, ArrayList<Album> album) {
            super(itemView);
            //register the onclick listener
            this.album = album;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            albumImageView = itemView.findViewById(R.id.albumImage);
            albumName = itemView.findViewById(R.id.albumName);
            artistName = itemView.findViewById(R.id.artistName);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Album album = this.album.get(position);
            Intent intent = new Intent(ctx, AlbumInfo.class);
            intent.putExtra("albumImageId", album.getImageId());
            intent.putExtra("albumName", album.getAlbumName());
            intent.putExtra("artistName", album.getArtistName());
            intent.putExtra("songList", album.getSongNames());
            ctx.startActivity(intent);
        }
    }
}