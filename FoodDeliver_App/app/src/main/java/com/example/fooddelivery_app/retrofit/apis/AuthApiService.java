package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Auth.SignUpModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApiService {
    @POST("Auth/Login") // endpoint của API đăng nhập
    Call<ResponseBody> loginUser(@Body LoginModel loginRequest);
    @POST("Auth/SignUp") // endpoint của API đăng nhập
    Call<ResponseBody> signUp(@Body SignUpModel signUpModel);
}
