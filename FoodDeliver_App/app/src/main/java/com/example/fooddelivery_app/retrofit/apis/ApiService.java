package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Auth/Login") // endpoint của API đăng nhập
    Call<ResponseBody> loginUser(@Body LoginModel loginRequest);
    @GET("User") // Change this to your real endpoint
    Call<List<User>> getUsers();
}
