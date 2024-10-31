package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.model.Shop.ShopDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShopApiService {
    @GET("User/Shop")
    Call<List<Shop>> getAllShops();

    @GET("Foods/{id}") // Adjust based on your actual endpoint
    Call<ShopDTO> getShopById(@Path("id") String id);
}
