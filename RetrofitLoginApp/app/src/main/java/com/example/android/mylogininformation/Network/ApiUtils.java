package com.example.android.mylogininformation.Network;


public class ApiUtils {
    //Base url
    public static final String BASE_URL = "http://ec2-54-69-219-242.us-west-2.compute.amazonaws.com:9000/v1/customer/";

    private ApiUtils() {
    }

    //Returning Retrofit Client
    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
