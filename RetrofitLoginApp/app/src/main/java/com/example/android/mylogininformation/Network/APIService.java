package com.example.android.mylogininformation.Network;

import com.example.android.mylogininformation.Models.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// Creating APIService Interface for POST Request and passing email and password in the body

public interface APIService {
    @POST("login/")
    @FormUrlEncoded
    Call<Post> savePost(@Field("email") String email,
                        @Field("password") String password);
}

