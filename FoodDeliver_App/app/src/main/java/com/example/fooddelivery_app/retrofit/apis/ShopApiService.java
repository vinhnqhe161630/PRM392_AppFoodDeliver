package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Shop.ShopViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ShopApiService {
    @GET("User/Shop")
    Call<List<ShopViewModel>> getAllShops();
}
