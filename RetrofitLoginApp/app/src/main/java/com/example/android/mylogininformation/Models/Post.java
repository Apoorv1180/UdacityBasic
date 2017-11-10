package com.example.android.mylogininformation.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Creating POJO Class for Post data
public class Post {

    @SerializedName("meta")
    @Expose
    private String meta;
    @SerializedName("data")
    @Expose
    private Data data;

    //Setter and Getter
    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}