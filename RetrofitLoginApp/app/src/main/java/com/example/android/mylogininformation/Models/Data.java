package com.example.android.mylogininformation.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Creating POJO Class for data (metadata)
public class Data {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("email")
    @Expose
    private String email;

    //Setter and Getters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}