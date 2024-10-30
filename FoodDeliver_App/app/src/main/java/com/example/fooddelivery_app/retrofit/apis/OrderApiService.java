package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Order.AddOrder;
import com.example.fooddelivery_app.model.Order.AddOrderDetails;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.model.Order.OrderDetails;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApiService {
    @GET("Order")
    Call<List<Order>> getAllOrders();

    @POST("Order/addOrder")
    Call<ResponseBody> addOrder(AddOrder addorder);

    @POST("Order/addOrderDetail")
    Call<ResponseBody> addOrderDetail(AddOrderDetails addOrderDetails);

    @GET("order/{userid}")
    Call<List<Order>> getOrderByUserId(@Path("userid") UUID userId);

    @GET("getOrderDetail/{orderid}")
    Call<List<OrderDetails>> getOrderDetail(@Path("orderid") UUID orderId);

}
