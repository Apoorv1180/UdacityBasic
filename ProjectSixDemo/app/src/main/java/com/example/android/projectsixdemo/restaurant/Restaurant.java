package com.example.android.projectsixdemo.restaurant;

public class Restaurant {
    private int imageId;
    private String hotelName;
    private String mRating;


    public Restaurant(int imageId, String hotelName, String rating) {
        this.imageId = imageId;
        this.hotelName = hotelName;
        this.mRating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getmRating() {
        return mRating;
    }
}
