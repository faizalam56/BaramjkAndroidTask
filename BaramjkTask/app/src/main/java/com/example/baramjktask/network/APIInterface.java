package com.example.baramjktask.network;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIInterface {

    @GET("signup/")
    Call<JsonObject> signupCall(@QueryMap Map<String,String> option);

    @GET("login/")
    Call<JsonObject> loginCall(@QueryMap Map<String,String> option);
}


