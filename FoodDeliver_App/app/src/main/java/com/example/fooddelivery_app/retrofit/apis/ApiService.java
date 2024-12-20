package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Comment.CommentDto;
import com.example.fooddelivery_app.model.Food.FoodDetailDto;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("User") // Change this to your real endpoint
    Call<List<User>> getUsers();




}