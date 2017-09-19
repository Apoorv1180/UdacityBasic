package com.example.android.projecteight;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<News> mNews;
    private Context ctx;

    public NewsAdapter(List<News> news, Context ctx) {
        this.mNews = news;
        this.ctx = ctx;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        NewsViewHolder newsHolder = new NewsViewHolder(view, ctx, mNews);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.NewsViewHolder holder, int position) {
        News news = mNews.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsType.setText(news.getType());
        holder.newsDate.setText(news.getDate());
        holder.newsSection.setText(news.getSection());

    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView newsTitle, newsType, newsDate, newsSection;
        List<News> news;
        Context ctx;
        private List<News> newsList;

        public NewsViewHolder(View itemView, Context ctx, List<News> news) {
            super(itemView);
            //register the onclick listener
            this.news = news;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsType = itemView.findViewById(R.id.news_type);
            newsDate = itemView.findViewById(R.id.news_date);
            newsSection = itemView.findViewById(R.id.news_section);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            News news = this.news.get(position);
            String url = news.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            ctx.startActivity(i);
        }
    }

    public void clear() {
        mNews.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<News> newsList) {
        mNews.addAll(newsList);
        notifyDataSetChanged();
    }
}