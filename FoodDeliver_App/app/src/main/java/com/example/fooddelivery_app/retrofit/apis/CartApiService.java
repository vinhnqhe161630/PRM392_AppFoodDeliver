package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Order.Cart;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartApiService {
    @GET("Cart")
    Call<List<Cart>> getAllCarts();
    @GET("cart/{userid}")
    Call<List<Cart>> getCartByUserId(@Path("userid") UUID userId);
}
