package com.example.oderapp.api;

import com.example.oderapp.activities.LoginRequest;
import com.example.oderapp.activities.LoginRespone;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.ResponseBodyDTO;
import com.example.oderapp.model.request.UserRequest;
import com.example.oderapp.model.response.ResponseBodyAddress;
import com.example.oderapp.model.response.ResponseBodyMethodOfPayment;
import com.example.oderapp.model.response.ResponseDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @POST("customer/login")
    Call<LoginRespone> loginUser(@Body LoginRequest loginRequest);

    // update infomation user
    @PATCH("customer/updateProfile")
    Call<ResponseDTO> updateInfo(@HeaderMap HashMap<String, String> hashMap, @Body UserRequest userRequest);

    @GET("/customer/profile")
    Call<ResponseBodyDTO> getProfile(@HeaderMap HashMap<String, String> hashMap);

    @DELETE("/customer/logout")
    Call<ResponseDTO> deleteUser(@Header("Authorization") String authorization);

    @POST("/address/add")
    Call<ResponseBodyAddress> insertAddress(@Header("Authorization") String authorization,@Body Address address);
    // get list address of user login
    @GET("/address")
    Call<ResponseBodyAddress> getListAddress(@HeaderMap HashMap<String, String> hashMap);

    @GET("/address/{id}")
    Call<ResponseBodyAddress> getAddress(@Path("id") int id,@HeaderMap HashMap<String, String> hashMap);

    @PATCH("/address/{id}")
    Call<ResponseDTO> updateAddress(@Path("id")int id,@Body Address address,@HeaderMap HashMap<String, String> hashMap);

    @DELETE("/address/{id}")
    Call<ResponseBodyAddress> deleteAddress(@Path("id") int id, @Header("Authorization") String authorization);

    @GET("/payment")
    Call<ResponseBodyMethodOfPayment> getListMethodOfPayment(@HeaderMap HashMap<String, String> hashMap);

    @GET("/payment/{id}")
    Call<ResponseBodyMethodOfPayment> getMethodOfPayment(@Path("id") int id,@HeaderMap HashMap<String, String> hashMap);
}
