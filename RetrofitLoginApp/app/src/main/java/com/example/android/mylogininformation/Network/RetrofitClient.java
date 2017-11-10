package com.example.android.mylogininformation.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Initialising Retrofit Client

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
