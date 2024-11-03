package com.example.fooddelivery_app.view.Shop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.FoodAdapter;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Shop.ShopDTO;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.FoodApiService;
import com.example.fooddelivery_app.retrofit.apis.ShopApiService;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodAdapter foodAdapter;
    private TextView tvShopName, tvAddress, tvEmail, tvPhone;
    private RatingBar ratingBar;
    private List<FoodDto> foodList;
    private ImageView imgShop;
    private static final String TAG = "ShopDetailActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopdetail);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_shop);
        recyclerView = findViewById(R.id.productRecyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView filterLowToHigh = findViewById(R.id.filter_low_to_high);
        TextView filterHighToLow = findViewById(R.id.filter_high_to_low);
        TextView bestSellerButton = findViewById(R.id.filter_best_seller);
        bestSellerButton.setOnClickListener(v -> foodAdapter.sortFoodListByCommentCount());

        filterLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortFoodListAscending();
            }
        });

        filterHighToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortFoodListDescending();
            }
        });

        TextView sortByRatingButton = findViewById(R.id.filter_high_rating);
        sortByRatingButton.setOnClickListener(v -> foodAdapter.sortFoodListByRating());

        // Initialize UI components
        tvShopName = findViewById(R.id.tvShopName);
        tvAddress = findViewById(R.id.address);
        tvEmail = findViewById(R.id.email);
        tvPhone = findViewById(R.id.phone);
        imgShop = findViewById(R.id.imgShop);
        ratingBar = findViewById(R.id.ratingBar1);

        String accountId = getIntent().getStringExtra("ShopId");

        getAccountById(accountId);
        // Gọi API
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<List<FoodDto>> call = apiService.getFoodsByAccountId(accountId);

        call.enqueue(new Callback<List<FoodDto>>() {
            @Override
            public void onResponse(Call<List<FoodDto>> call, Response<List<FoodDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                     foodList = response.body();
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
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_cart:
                    // Open OrderActivity when Order menu item is clicked
                    Intent cartIntent = new Intent(this, CartActivity.class);
                    startActivity(cartIntent);
                    finish();
                    return true;
                case R.id.navigation_More:
                    // Open OrderActivity when Order menu item is clicked
                    Intent orderIntent = new Intent(this, OrderListActivity.class);
                    startActivity(orderIntent);
                    finish();
                    return true;
                case R.id.navigation_blog:
                    // Open CartActivity when Cart menu item is clicked
                    Intent shopIntent = new Intent(this, BlogActivity.class);
                    startActivity(shopIntent);
                    finish();
                    return true;
                case R.id.navigation_Rank:
                    // Open CartActivity when Cart menu item is clicked
                    Intent rankIntent = new Intent(this, ShopVotedActivity.class);
                    startActivity(rankIntent);
                    finish();
                    return true;
                default:
                    Intent homeIntent = new Intent(this, MainActivity.class);
                    startActivity(homeIntent);
                    finish();
                    return true;
                // Handle other menu items here

            }
        });
    }
    private void sortFoodListAscending() {
        if (foodList != null) { // Ensure foodList is not null
            Collections.sort(foodList, new Comparator<FoodDto>() {
                @Override
                public int compare(FoodDto item1, FoodDto item2) {
                    return Double.compare(item1.getPrice(), item2.getPrice());
                }
            });
            foodAdapter.notifyDataSetChanged(); // Notify adapter after sorting
        }
    }

    private void sortFoodListDescending() {
        if (foodList != null) { // Ensure foodList is not null
            Collections.sort(foodList, new Comparator<FoodDto>() {
                @Override
                public int compare(FoodDto item1, FoodDto item2) {
                    return Double.compare(item2.getPrice(), item1.getPrice());
                }
            });
            foodAdapter.notifyDataSetChanged(); // Notify adapter after sorting
        }
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
        Glide.with(ShopDetailActivity.this)
                .load(account.getImg())
                .into(imgShop);
        // Set the RatingBar to the average rating
        ratingBar.setRating(averageRating);
    }
}
