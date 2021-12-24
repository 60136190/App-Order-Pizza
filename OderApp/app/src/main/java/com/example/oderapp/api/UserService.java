package com.example.oderapp.api;

import com.example.oderapp.activities.LoginRequest;
import com.example.oderapp.activities.LoginRespone;
import com.example.oderapp.model.Address;
import com.example.oderapp.model.Rating;
import com.example.oderapp.model.request.ChangePasswordRequest;
import com.example.oderapp.model.request.ForgotPasswordRequest;
import com.example.oderapp.model.request.UserRequest;
import com.example.oderapp.model.response.ReponseUrl;
import com.example.oderapp.model.response.ResponseBodyAddress;
import com.example.oderapp.model.response.ResponseBodyMethodOfPayment;
import com.example.oderapp.model.response.ResponseChangePasswordDTO;
import com.example.oderapp.model.response.ResponseDTO;
import com.example.oderapp.model.response.ResponseForgotPassword;
import com.example.oderapp.model.response.ResponseInformationUser;
import com.example.oderapp.model.response.ResponseRating;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {
    @POST("customer/login")
    Call<LoginRespone> loginUser(@Body LoginRequest loginRequest);

    // update infomation user
    @PATCH("customer/updateProfile")
    Call<ResponseDTO> updateInfo(@HeaderMap HashMap<String, String> hashMap, @Body UserRequest userRequest);

    //forgot password
    @POST("customer/forgotPassword")
    Call<ResponseForgotPassword> forgotPassword (@HeaderMap HashMap<String, String> hashMap,@Body ForgotPasswordRequest forgotPassword);

    // change password
    @PATCH("customer/changePassword")
    Call<ResponseChangePasswordDTO> changePasswordUser(@HeaderMap HashMap<String, String> hashMap, @Body ChangePasswordRequest changePasswordRequest);

    @GET("/customer/profile")
    Call<ResponseInformationUser> getProfile(@HeaderMap HashMap<String, String> hashMap);

    @DELETE("/customer/logout")
    Call<ResponseDTO> deleteUser(@HeaderMap  HashMap<String,String> authorization);

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

    @POST("/rating/add/{id}")
    Call<ResponseRating> ratingBill(@Path("id") int id, @Body Rating rating, @HeaderMap HashMap<String, String> hashMap);

    @Multipart
    @POST("/cloud/uploadUserImage/customer")
    Call<ReponseUrl> uploadImage(@HeaderMap HashMap<String,String> hashMap,@Part MultipartBody.Part imgFile );
}

