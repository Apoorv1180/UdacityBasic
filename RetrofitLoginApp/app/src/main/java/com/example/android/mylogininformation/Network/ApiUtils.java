package com.example.android.mylogininformation.Network;

/**
 * Created by Apoorv on 09-11-2017.
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://ec2-54-69-219-242.us-west-2.compute.amazonaws.com:9000/v1/customer/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
