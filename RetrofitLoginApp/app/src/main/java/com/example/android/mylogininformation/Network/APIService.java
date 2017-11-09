package com.example.android.mylogininformation.Network;




import com.example.android.mylogininformation.Models.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Apoorv on 09-11-2017.
 */

public interface APIService {
    @POST("login/")
    @FormUrlEncoded
    Call<Post> savePost(@Field("email") String email,
                        @Field("password") String password);
}

