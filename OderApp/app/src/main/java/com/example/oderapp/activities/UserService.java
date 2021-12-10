package com.example.oderapp.activities;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("customer/login")
    Call<LoginRespone> loginUser(@Body LoginRequest loginRequest);
}
