package com.example.fooddelivery_app.view.Shop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.FoodAdapter;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Shop.ShopDTO;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.FoodApiService;
import com.example.fooddelivery_app.retrofit.apis.ShopApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private TextView tvShopName, tvAddress, tvEmail, tvPhone;
    private RatingBar ratingBar;
    private static final String TAG = "ShopDetailActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);

        recyclerView = findViewById(R.id.productRecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize UI components
        tvShopName = findViewById(R.id.tvShopName);
        tvAddress = findViewById(R.id.address);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);

        ratingBar = findViewById(R.id.ratingBar1);

        // Get the average rating from your foodDetail object

        ratingBar = findViewById(R.id.ratingBar1);
        // Giả sử accountId đã được cung cấp
        String accountId = "2cda8ad7-1ea9-4a56-0daa-08dcf985ada7"; // Thay bằng accountId thực tế
        getAccountById(accountId);
        // Gọi API
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<List<FoodDto>> call = apiService.getFoodsByAccountId(accountId);

        call.enqueue(new Callback<List<FoodDto>>() {
            @Override
            public void onResponse(Call<List<FoodDto>> call, Response<List<FoodDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FoodDto> foodList = response.body();
                    Log.d(TAG, "Received food list: " + foodList.size());
                    foodAdapter = new FoodAdapter(ShopDetailActivity.this, foodList);
                    recyclerView.setAdapter(foodAdapter);
                } else {
                    Log.e(TAG, "Response unsuccessful or body is null. Code: " + response.code());
                    Toast.makeText(ShopDetailActivity.this, "No food found", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<FoodDto>> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(ShopDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(ShopDetailActivity.this, "Failed to load food list", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getAccountById(String id) {
        ShopApiService apiService = RetrofitUtility.getClient().create(ShopApiService.class);
        Call<ShopDTO> call = apiService.getShopById(id);

        call.enqueue(new Callback<ShopDTO>() {
            @Override
            public void onResponse(Call<ShopDTO> call, Response<ShopDTO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ShopDTO account = response.body();
                    // Bind data to UI
                    bindDataToUI(account);
                } else {
                    Toast.makeText(ShopDetailActivity.this, "Account not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ShopDTO> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(ShopDetailActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindDataToUI(ShopDTO account) {
        tvShopName.setText(account.getName());
        tvAddress.setText("Address: " + account.getAddress());
        tvEmail.setText("Email: " + account.getEmail());
        tvPhone.setText("Phone: " + account.getPhone());

        float averageRating = (float) account.calculateShopAverageRating(); // Make sure this method returns a float

        // Set the RatingBar to the average rating
        ratingBar.setRating(averageRating);
    }
}
