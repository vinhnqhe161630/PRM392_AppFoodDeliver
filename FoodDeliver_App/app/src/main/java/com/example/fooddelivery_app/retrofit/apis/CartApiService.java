package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Order.AddCart;
import com.example.fooddelivery_app.model.Order.AddOrder;
import com.example.fooddelivery_app.model.Order.AddOrderDetails;
import com.example.fooddelivery_app.model.Order.Cart;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartApiService {
    @GET("Cart")
    Call<List<Cart>> getAllCarts();
    @POST("Cart")
    Call<Cart> addToCart(@Body AddCart addCart);
    @GET("cart/{userid}")
    Call<List<Cart>> getCartByUserId(@Path("userid") UUID userId);
    @POST("order/checkout/{userid}")
    Call<AddOrder> checkOut(@Path("userid") UUID userId);
    @DELETE("cart/{cartId}")
    Call<Cart> deleteCart(@Path("cartId") UUID cartId);
    @POST("cart/increase/{cartId}")
    Call<Cart> increaseCart(@Path("cartId") UUID cartId);
    @POST("cart/decrease/{cartId}")
    Call<Cart> decreaseCart(@Path("cartId") UUID cartId);
}
