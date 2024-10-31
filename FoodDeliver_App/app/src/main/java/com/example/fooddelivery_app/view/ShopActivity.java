package com.example.fooddelivery_app.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.FoodAdapter;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private static final String TAG = "ShopActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        recyclerView = findViewById(R.id.recycler_view_food);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Giả sử accountId đã được cung cấp
        String accountId = "8853b0e3-f704-4286-e791-08dcf90bb6ed"; // Thay bằng accountId thực tế

        // Gọi API
        ApiService apiService = RetrofitUtility.getClient().create(ApiService.class);
        Call<List<FoodDto>> call = apiService.getFoodsByAccountId(accountId);

        call.enqueue(new Callback<List<FoodDto>>() {
            @Override
            public void onResponse(Call<List<FoodDto>> call, Response<List<FoodDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FoodDto> foodList = response.body();
                    Log.d(TAG, "Received food list: " + foodList.size());
                    foodAdapter = new FoodAdapter(ShopActivity.this, foodList);
                    recyclerView.setAdapter(foodAdapter);
                } else {
                    Log.e(TAG, "Response unsuccessful or body is null. Code: " + response.code());
                    Toast.makeText(ShopActivity.this, "No food found", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<FoodDto>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(ShopActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(ShopActivity.this, "Failed to load food list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
