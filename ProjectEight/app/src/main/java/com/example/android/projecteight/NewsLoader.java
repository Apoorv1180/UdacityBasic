package com.example.android.projecteight;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mNewsUrl;

    public NewsLoader(Context ctx, String url) {
        super(ctx);
        mNewsUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        if (mNewsUrl.length() < 1 || mNewsUrl == null) {
            return null;
        }
        List<News> news = Utils.fetchNewsData(mNewsUrl);
        return news;
    }
}
