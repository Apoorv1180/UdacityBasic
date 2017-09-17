package com.example.android.musicstructureapp.Artist;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicstructureapp.R;

import java.util.ArrayList;


public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder> {

    public ArrayList<Artist> mArtist = new ArrayList<Artist>();
    Context ctx;

    public ArtistAdapter(ArrayList<Artist> artist, Context ctx) {
        this.mArtist = artist;
        this.ctx = ctx;
    }

    @Override
    public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist, parent, false);
        ArtistHolder artistHolder = new ArtistHolder(view, ctx, mArtist);
        return artistHolder;
    }

    @Override
    public void onBindViewHolder(ArtistHolder holder, int position) {
        Artist artist = mArtist.get(position);
        // holder..setText(artist.getArtistName());
        holder.artistName.setText(artist.getArtistName());
    }

    @Override
    public int getItemCount() {
        return mArtist.size();
    }


    public static class ArtistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView artistName;
        ArrayList<Artist> artist = new ArrayList<Artist>();
        Context ctx;

        public ArtistHolder(View itemView, Context ctx, ArrayList<Artist> artist) {
            super(itemView);
            //register the onclick listener
            this.ctx = ctx;
            this.artist = artist;
            itemView.setOnClickListener(this);
            artistName = itemView.findViewById(R.id.artistNameFrag);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Artist artist = this.artist.get(position);
            Toast.makeText(this.ctx, position + "pos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx, ArtistInfo.class);
            intent.putExtra("artistName", artist.getArtistName());
            intent.putExtra("songList", artist.getSongNames());
            intent.putExtra("albumList", artist.getAlbumNames());
            ctx.startActivity(intent);
        }
    }
}