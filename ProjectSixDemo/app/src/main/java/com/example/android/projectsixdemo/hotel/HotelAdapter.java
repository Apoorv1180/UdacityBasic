package com.example.android.projectsixdemo.hotel;


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

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.hotelViewHoder> {


    public ArrayList<Hotel> mAlbum = new ArrayList<Hotel>();

    Context ctx;

    public HotelAdapter(ArrayList<Hotel> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public hotelViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_card_view, parent, false);
        hotelViewHoder albumHolder = new hotelViewHoder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(hotelViewHoder holder, int position) {
        Hotel album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.albumName.setText(album.getHotelName());
        holder.rating.setText(album.getmRating());
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }


    public static class hotelViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView albumImageView;
        TextView albumName, rating;
        ArrayList<Hotel> album = new ArrayList<Hotel>();
        Context ctx;

        public hotelViewHoder(View itemView, Context ctx, ArrayList<Hotel> album) {
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
            Hotel album = this.album.get(position);
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.putExtra("HotelPosition", String.valueOf(position));
            intent.putExtra("HotelName", album.getHotelName());
            intent.putExtra("activity", "HotelActivity");
            ctx.startActivity(intent);
        }
    }
}