package com.example.oderapp.api;

import com.example.oderapp.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    //Link API https://api.agify.io/?name=meelad

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH-mm-ss")
            .create();

    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("https://api.agify.io")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("/")
    Call<User> getListUser(@Query("name") String key);
}
