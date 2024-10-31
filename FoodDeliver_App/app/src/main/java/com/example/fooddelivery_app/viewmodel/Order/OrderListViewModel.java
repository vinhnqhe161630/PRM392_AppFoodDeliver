package com.example.fooddelivery_app.viewmodel.Order;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.model.Order.OrderDetails;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.OrderRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class OrderListViewModel extends ViewModel {
    private OrderRepository orderRepository;

    public OrderListViewModel() {
        orderRepository = new OrderRepository();

    }

    public LiveData<List<Order>> getOrderByUserId(UUID userId) {


        return  orderRepository.getOrderByUserId(userId);
    }
    public LiveData<List<OrderDetails>> getOrderDetailsByUserId(UUID userId,Context context) {


        return  orderRepository.getOrderDetail(userId,context);
    }

    public static String getUserIdFromToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String authToken = sharedPreferences.getString("auth_token", null);

        if (authToken == null) {
            return null; // Token not available
        }

        // JWT tokens are typically in the format: header.payload.signature
        String[] parts = authToken.split("\\.");
        if (parts.length < 2) {
            return null; // Invalid JWT token
        }

        // Decode the payload (part[1]) which is base64-encoded
        String payload = new String(Base64.decode(parts[1], Base64.URL_SAFE));

        try {
            // Parse the payload as JSON and extract userId
            JSONObject jsonObject = new JSONObject(payload);
            String userId = jsonObject.getString("userId"); // Extract userId from the payload

            // Convert userId to uppercase
            return userId.toUpperCase(); // Change to lowercase if you prefer: userId.toLowerCase()
        } catch (JSONException e) {
            e.printStackTrace();
            return null; // Handle error in payload parsing
        }
    }



}
