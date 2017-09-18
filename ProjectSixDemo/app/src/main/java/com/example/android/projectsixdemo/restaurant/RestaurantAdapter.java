package com.example.android.projectsixdemo.restaurant;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.projectsixdemo.other.MapsActivity;
import com.example.android.projectsixdemo.R;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    public ArrayList<Restaurant> mAlbum = new ArrayList<Restaurant>();
    Context ctx;

    public RestaurantAdapter(ArrayList<Restaurant> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_card_view, parent, false);
        RestaurantViewHolder albumHolder = new RestaurantViewHolder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        Restaurant album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.albumName.setText(album.getHotelName());
        holder.rating.setText(album.getmRating());
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView albumImageView;
        TextView albumName, rating;
        ArrayList<Restaurant> album = new ArrayList<Restaurant>();
        Context ctx;

        public RestaurantViewHolder(View itemView, Context ctx, ArrayList<Restaurant> album) {
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
            Restaurant album = this.album.get(position);
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.putExtra(String.valueOf(R.string.RestaurantPosition), position);
            intent.putExtra(String.valueOf(R.string.RestaurantName), album.getHotelName());
            intent.putExtra(String.valueOf(R.string.activity), String.valueOf(R.string.RestaurantActivity));
            ctx.startActivity(intent);
        }
    }
}