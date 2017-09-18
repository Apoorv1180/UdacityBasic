package com.example.android.projectsixdemo.nightlife;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.projectsixdemo.R;
import com.example.android.projectsixdemo.other.MapsActivity;

import java.util.ArrayList;

public class NightlifeAdapter extends RecyclerView.Adapter<NightlifeAdapter.NightLifeViewHolder> {

    public ArrayList<NightLife> mAlbum = new ArrayList<NightLife>();
    Context ctx;

    public NightlifeAdapter(ArrayList<NightLife> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public NightLifeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_card_view, parent, false);
        NightLifeViewHolder albumHolder = new NightLifeViewHolder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(NightLifeViewHolder holder, int position) {
        NightLife album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.albumName.setText(album.getHotelName());
        holder.rating.setText(album.getmRating());

    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }

    public static class NightLifeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView albumImageView;
        TextView albumName, rating;
        ArrayList<NightLife> album = new ArrayList<NightLife>();
        Context ctx;

        public NightLifeViewHolder(View itemView, Context ctx, ArrayList<NightLife> album) {
            super(itemView);
            //register the onclick listener
            this.album = album;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            albumImageView = itemView.findViewById(R.id.hotelImage);
            albumName = itemView.findViewById(R.id.hotelName);
            rating = itemView.findViewById(R.id.rating);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            NightLife album = this.album.get(position);
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.putExtra(String.valueOf(R.string.NightlifePosition), position);
            intent.putExtra(String.valueOf(R.string.NightlifeName), album.getHotelName());
            intent.putExtra(String.valueOf(R.string.activity), String.valueOf(R.string.NightLifeActivity));
            ctx.startActivity(intent);
        }
    }
}