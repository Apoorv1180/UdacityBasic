package com.example.android.projectsixdemo.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.projectsixdemo.other.HowToReachActivity;
import com.example.android.projectsixdemo.other.MapsActivity;
import com.example.android.projectsixdemo.R;
import com.example.android.projectsixdemo.hotel.HotelActivity;
import com.example.android.projectsixdemo.nightlife.NightLifeActivity;
import com.example.android.projectsixdemo.places.PlacesToVisitActivity;
import com.example.android.projectsixdemo.restaurant.RestaurantActivity;

import java.util.ArrayList;

public class homeCardViewAdapter extends RecyclerView.Adapter<homeCardViewAdapter.homeCardViewHolder> {


    public ArrayList<homeCardViewInfo> mAlbum = new ArrayList<homeCardViewInfo>();
    Context ctx;

    public homeCardViewAdapter(ArrayList<homeCardViewInfo> album, Context ctx) {
        this.mAlbum = album;
        this.ctx = ctx;
    }

    @Override
    public homeCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        homeCardViewHolder albumHolder = new homeCardViewHolder(view, ctx, mAlbum);
        return albumHolder;
    }

    @Override
    public void onBindViewHolder(homeCardViewHolder holder, int position) {
        homeCardViewInfo album = mAlbum.get(position);
        holder.albumImageView.setImageResource(album.getImageId());
        holder.albumName.setText(album.getName());

    }

    @Override
    public int getItemCount() {
        return mAlbum.size();
    }

    public static class homeCardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView albumImageView;
        TextView albumName;
        ArrayList<homeCardViewInfo> album = new ArrayList<homeCardViewInfo>();
        Context ctx;

        public homeCardViewHolder(View itemView, Context ctx, ArrayList<homeCardViewInfo> album) {
            super(itemView);
            //register the onclick listener
            this.album = album;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            albumImageView = itemView.findViewById(R.id.circularImage);
            albumName = itemView.findViewById(R.id.homeCardViewText);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(ctx, HowToReachActivity.class);
                    ctx.startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(ctx, HotelActivity.class);
                    ctx.startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(ctx, RestaurantActivity.class);
                    ctx.startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(ctx, PlacesToVisitActivity.class);
                    ctx.startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(ctx, NightLifeActivity.class);
                    ctx.startActivity(intent);
                    break;
                case 5:
                    intent = new Intent(ctx, MapsActivity.class);
                    String[] homeCardViewOptionsLatitudes = ctx.getResources().getStringArray(R.array.latitudes);
                    String[] homeCardViewOptionsLongitudes = ctx.getResources().getStringArray(R.array.longitudes);
                    intent.putExtra(String.valueOf(R.string.activity), String.valueOf(R.string.MapsActivity));
                    intent.putExtra(String.valueOf(R.string.latitudearray), homeCardViewOptionsLatitudes);
                    intent.putExtra(String.valueOf(R.string.longitudearray), homeCardViewOptionsLongitudes);
                    ctx.startActivity(intent);
                    break;
                default:
                    Toast.makeText(ctx, position, Toast.LENGTH_SHORT).show();
            }
        }
    }
}