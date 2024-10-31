package com.example.fooddelivery_app.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Order.Cart;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.CartApiService;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private CartApiService cartApiService;

    public CartRepository() {
        cartApiService = RetrofitUtility.getClient().create(CartApiService.class);;
    }
    public LiveData<List<Cart>> getCartByUserId(UUID userId) {
        MutableLiveData<List<Cart>> liveData = new MutableLiveData<>();
        Call<List<Cart>> call = cartApiService.getCartByUserId(userId);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    // Update LiveData with the fetched data
                    liveData.setValue(response.body());
                } else {
                    // Handle the response error here if needed
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                // Handle the failure, e.g., log error or notify user
                liveData.setValue(null);
            }
        });

        return liveData;

    }
}
