package com.example.oderapp.api;

import com.example.oderapp.model.ItemBill;
import com.example.oderapp.model.ItemFood;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.model.response.ResponseBodyAddress;
import com.example.oderapp.model.response.ResponseBodyBill;
import com.example.oderapp.model.response.ResponseBodyCart;
import com.example.oderapp.model.response.ResponseDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
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

    @DELETE("/cart/delete/{id}")
    Call<ResponseBodyCart> deleteItemCart(@Path("id") int id, @Header("Authorization") String authorization);

    @GET("/bill/history")
    Call<ResponseBodyBill> getAllBill(@HeaderMap HashMap<String, String> hashMap);

    // cancel bill with status method payment  is COD
    @PATCH("/bill/cancel/{id}")
    Call<ResponseBodyBill> cancelBill(@Path("id") int id,@Header("Authorization") String authorization);

}
