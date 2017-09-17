package com.example.android.projectsixdemo.nightlife;

public class NightLife {
    private int imageId;
    private String hotelName;
    private String mRating;


    public NightLife(int imageId, String hotelName, String rating) {
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
