package com.example.oderapp.api;

import com.example.oderapp.model.Currency;
import com.example.oderapp.model.GetItemCart;
import com.example.oderapp.model.GetProfile;
import com.example.oderapp.model.ItemCart;
import com.example.oderapp.model.User;
import com.example.oderapp.model.UserRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //Link API https://api.agify.io/?name=meelad
    //Link API register http://localhost:5000/users
    // http://localhost:5000/customer/register

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("http://192.168.1.14:5000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

//    @POST("users")
//    @FormUrlEncoded
//    Call<List<User>> getListUsers (@Field("email") String username,
//                                   @Field("password") String password);

// @POST("users")
//    Call<List<RegisterUser>> sendPost(@Body RegisterUser registerUser);

//  @POST("users")
//  Call<List<UserRespone>> saveUser(@Body UserRequest userRequest);


    // login
//    @POST("customdsader/dsadsadsada")
//    Call<User> sendLogin(@Body User user);

    @POST("customer/register")
    Call<UserRegister> sendPost(@Body UserRegister userRegister);
//    @Header("Authorization") String authHeader
//    @GET("cart")
//    Call<List<ItemCart>> getcart();

//    @Header("Authorization") String authHeader
    @POST("cart/addCartItem/{id_sp}")
    Call<ItemCart> addCart(@Body ItemCart itemCart,
            @Path("id_sp") int id_sp,
                           @Header("Authorization") String authHeader);

    @GET("customer/profile")
    Call<GetProfile> getProfile();

}
