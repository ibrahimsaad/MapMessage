package com.example.ibrahim.mapmessage.network;

import com.example.ibrahim.mapmessage.network.pojo.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("0Ai2EnLApq68edEVRNU0xdW9QX1BqQXhHRl9sWDNfQXc/od6/public/basic?alt=json")
    Call<ResponseData> fetchMessages();

}
