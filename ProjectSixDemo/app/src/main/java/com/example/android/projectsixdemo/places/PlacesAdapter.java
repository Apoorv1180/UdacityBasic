package com.example.android.projectsixdemo.places;


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

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesToVisitViewHoder> {


    public ArrayList<PlacesToVisit> mAlbum = new ArrayList<PlacesToVisit>();
    Context ctx;

    public PlacesAdapter(ArrayList<PlacesToVisit> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public PlacesToVisitViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_card_view, parent, false);
        PlacesToVisitViewHoder albumHolder = new PlacesToVisitViewHoder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(PlacesToVisitViewHoder holder, int position) {
        PlacesToVisit album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.albumName.setText(album.getHotelName());
        holder.rating.setText(album.getmRating());
    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }


    public static class PlacesToVisitViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView albumImageView;
        TextView albumName, rating;
        ArrayList<PlacesToVisit> album = new ArrayList<PlacesToVisit>();
        Context ctx;

        public PlacesToVisitViewHoder(View itemView, Context ctx, ArrayList<PlacesToVisit> album) {
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
            PlacesToVisit album = this.album.get(position);
            Intent intent = new Intent(ctx, MapsActivity.class);
            intent.putExtra("PlacePosition", position);
            intent.putExtra("PlaceName", album.getHotelName());
            intent.putExtra("activity", "PlacesToVisitActivity");
            ctx.startActivity(intent);
        }
    }
}