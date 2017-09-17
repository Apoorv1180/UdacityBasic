package com.example.android.musicstructureapp.Playlist;


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


public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder> {

    public ArrayList<Playlist> mPlaylist = new ArrayList<Playlist>();
    Context ctx;

    public PlaylistAdapter(ArrayList<Playlist> playlist, Context ctx) {

        this.mPlaylist = playlist;
        this.ctx = ctx;
    }

    @Override
    public PlaylistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist, parent, false);
        PlaylistHolder playlistHolder = new PlaylistHolder(view, ctx, mPlaylist);
        return playlistHolder;
    }

    @Override
    public void onBindViewHolder(PlaylistHolder holder, int position) {
        Playlist playlist = mPlaylist.get(position);
        holder.playlistName.setText(playlist.getPlaylistName());
    }

    @Override
    public int getItemCount() {
        return mPlaylist.size();
    }

    public static class PlaylistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView playlistName;
        ArrayList<Playlist> mPlaylist = new ArrayList<Playlist>();
        Context ctx;

        public PlaylistHolder(View itemView, Context ctx, ArrayList<Playlist> playlist) {
            super(itemView);
            //register the onclick listener
            this.ctx = ctx;
            this.mPlaylist = playlist;
            itemView.setOnClickListener(this);
            playlistName = itemView.findViewById(R.id.playlistNameFrag);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Playlist mPlaylist = this.mPlaylist.get(position);
            Toast.makeText(this.ctx, position + "pos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx, PlaylistInfo.class);
            intent.putExtra("playListName", mPlaylist.getPlaylistName());
            intent.putExtra("songList", mPlaylist.getSongNames());
            ctx.startActivity(intent);
        }
    }
}