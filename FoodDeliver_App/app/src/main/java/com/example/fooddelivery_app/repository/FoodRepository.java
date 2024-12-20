package com.example.fooddelivery_app.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Food.Food;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.FoodApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class FoodRepository {
    private FoodApiService apiService;
    public FoodRepository() {
        apiService = RetrofitUtility.getClient().create(FoodApiService.class);
    }

    public MutableLiveData<List<FoodDto>> getFoods() {

        MutableLiveData<List<FoodDto>> liveData = new MutableLiveData<>();
        Call<List<FoodDto>> call = apiService.getFoods();

        call.enqueue(new Callback<List<FoodDto>>() {
            @Override
            public void onResponse(Call<List<FoodDto>> call, retrofit2.Response<List<FoodDto>> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<FoodDto>> call, Throwable t) {
                liveData.postValue(null);  // Hoặc gửi thông báo lỗi
                t.printStackTrace(); // Hoặc log lỗi, hoặc xử lý thêm
            }
        });

        return liveData;
    }

}
