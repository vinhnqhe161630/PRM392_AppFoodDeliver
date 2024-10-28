package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.ChangePasswordModel;
import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Auth.SignUpModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApiService {
    @POST("Auth/Login")
    Call<ResponseBody> loginUser(@Body LoginModel loginRequest);

    @POST("Auth/SignUp")
    Call<ResponseBody> signUp(@Body SignUpModel signUpModel);

    @POST("Auth/ChangePassword")
    Call<ResponseBody> changePass(@Body ChangePasswordModel changePasswordModel);

    @POST("Auth/ForgotPassword")
    Call<ResponseBody> forgotPass(@Query("email") String email);


}
