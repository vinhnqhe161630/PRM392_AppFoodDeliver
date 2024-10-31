package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Food.Food;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface FoodApiService {
@GET("Food")
Call<List<Food>> getFoods();


}
