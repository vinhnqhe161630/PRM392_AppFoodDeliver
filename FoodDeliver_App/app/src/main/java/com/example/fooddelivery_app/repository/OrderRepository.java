package com.example.fooddelivery_app.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Order.AddOrder;
import com.example.fooddelivery_app.model.Order.AddOrderDetails;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.model.Order.OrderDetails;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.AuthApiService;
import com.example.fooddelivery_app.retrofit.apis.OrderApiService;
import com.example.fooddelivery_app.view.Order.OrderDetailsActivity;

import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {

    private OrderApiService orderApiService;

    public OrderRepository() {
        orderApiService = RetrofitUtility.getClient().create(OrderApiService.class);;
    }

// Add order
    public LiveData<String> addOrder(AddOrder addOrder ) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<ResponseBody> call = orderApiService.addOrder(addOrder);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                   liveData.postValue("Order added successfully");
                } else {
                    handleErrorResponse(response, liveData);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveData.postValue("API error: " + t.getMessage());
            }
        });
        return liveData;
    }

    //Add Order Details
    public LiveData<String> addOrderDetails(AddOrderDetails addOrderDetails ) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        Call<ResponseBody> call = orderApiService.addOrderDetail(addOrderDetails);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue("OrderDetails added successfully");
                } else {
                    handleErrorResponse(response, liveData);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveData.postValue("API error: " + t.getMessage());
            }
        });
        return liveData;
    }

    //Get order by User ID
    public LiveData<List<Order>> getOrderByUserId(UUID userId) {
        MutableLiveData<List<Order>> liveData = new MutableLiveData<>();
        Call<List<Order>> call = orderApiService.getOrderByUserId(userId);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    // Update LiveData with the fetched data
                    liveData.setValue(response.body());
                } else {
                    // Handle the response error here if needed
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                // Handle the failure, e.g., log error or notify user
                liveData.setValue(null);
            }
        });

        return liveData;

    }

//Get order Details by order id
    public LiveData<List<OrderDetails>> getOrderDetail(UUID orderId, Context context) {
        MutableLiveData<List<OrderDetails>> liveData = new MutableLiveData<>();
        Call<List<OrderDetails>> call = orderApiService.getOrderDetail(orderId);

        call.enqueue(new Callback<List<OrderDetails>>() {
            @Override
            public void onResponse(Call<List<OrderDetails>> call, Response<List<OrderDetails>> response) {
                if (response.isSuccessful()) {
                    // Update LiveData with the fetched data
                    liveData.setValue(response.body());
                } else {
                    // Handle the response error here if needed
                    liveData.setValue(null);
                    Toast.makeText(context, "Failed to fetch order details. null value", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<OrderDetails>> call, Throwable t) {
                // Handle the failure, e.g., log error or notify user
                liveData.setValue(null);
                Toast.makeText(context, "Failed to fetch order details. "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return liveData;

    }

    private void handleErrorResponse(Response<ResponseBody> response, MutableLiveData<String> liveData) {
        try {
            String responseBody = response.errorBody().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            String message = jsonObject.getString("message");
            liveData.postValue("Fail: " + message);
        } catch (Exception e) {
            e.printStackTrace();
            liveData.postValue("Error parsing error response");
        }
    }
}
