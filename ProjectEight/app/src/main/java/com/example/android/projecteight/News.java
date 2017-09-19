package com.example.android.projecteight;

//News class
public class News {
    private String mNewsTitle;
    private String mNewsCategory;
    private String mNewsDate;
    private String mNewsSection;
    private String mNewsUrl;

    public News(String title, String category, String date, String section, String url) {
        this.mNewsTitle = title;
        this.mNewsCategory = category;
        this.mNewsDate = date;
        this.mNewsSection = section;
        this.mNewsUrl = url;
    }

    public String getTitle() {
        return "Title :" + mNewsTitle;
    }

    public String getType() {
        return mNewsCategory;
    }

    public String getDate() {
        return mNewsDate;
    }

    public String getSection() {
        return "Section :" + mNewsSection;
    }

    public String getUrl() {
        return mNewsUrl;
    }
}
