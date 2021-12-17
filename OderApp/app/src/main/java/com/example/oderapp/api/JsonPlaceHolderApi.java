package com.example.oderapp.api;

import com.example.oderapp.model.ItemCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
//    @GET("cart")
//    Call<ItemCart> getCart(@Header("Authorization") String authHeader);



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("cart")
    Call<ItemCart> getUser( @Header("Authorization") String auth);
}
