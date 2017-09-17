package com.example.android.projectsixdemo.home;

public class homeCardViewInfo {
    private int mImageId;
    private String mName;

    public homeCardViewInfo(int mImageId, String mName) {
        this.mImageId = mImageId;
        this.mName = mName;
    }

    public int getImageId() {
        return mImageId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
