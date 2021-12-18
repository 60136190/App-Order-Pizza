package com.example.oderapp.api;

import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.model.response.ResponseDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductServer {
    @POST("/cart/addCartItem/{id_sp}")
    Call<ResponseBodyDTO> insertCart(@Path("id_sp") int id_sp, @Header("Authorization") String authorization);

    @GET("/cart")
    Call<ResponseBodyCart> getCart(@HeaderMap HashMap<String, String> hashMap);

    @GET("/product/{id}")
    Call<ItemFood> getDescription(@Path("id") int id);


}
