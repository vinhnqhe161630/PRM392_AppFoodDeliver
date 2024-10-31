package com.example.fooddelivery_app.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.CommentAdapter;
import com.example.fooddelivery_app.model.Comment.CommentDto;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity {

    private static final String TAG = "FoodDetailActivity";
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView;
    private TextView accountNameTextView;
    private ImageView productImageView, accountImageView;
    private CommentAdapter commentAdapter;
    private RecyclerView commentsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        // Initialize UI components
        productNameTextView = findViewById(R.id.productNameTextView);
        productImageView = findViewById(R.id.productImageView);
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);

        // New components for account details
        accountNameTextView = findViewById(R.id.accountNameTextView);
        accountImageView = findViewById(R.id.accountImageView);

        // Initialize RecyclerView for comments
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the foodId from the Intent
        String foodId = getIntent().getStringExtra("foodId");
        if (foodId != null) {
            getFoodDetail(foodId);
            getCommentsByFoodId(foodId);
        } else {
            Toast.makeText(this, "Food ID is missing", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void getFoodDetail(String foodId) {
        ApiService apiService = RetrofitUtility.getClient().create(ApiService.class);
        Call<FoodDto> call = apiService.getFoodDetail(foodId);

        call.enqueue(new Callback<FoodDto>() {
            @Override
            public void onResponse(Call<FoodDto> call, Response<FoodDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FoodDto foodDetail = response.body();
                    Log.d(TAG, "Food detail: " + foodDetail.getName());

                    // Update UI with food details
                    productNameTextView.setText(foodDetail.getName());
                    productDescriptionTextView.setText(foodDetail.getDescription());
                    productPriceTextView.setText(String.format("$%.2f", foodDetail.getPrice()));

                    // Load food image
                    Glide.with(FoodDetailActivity.this)
                            .load(foodDetail.getImg())
                            .into(productImageView);

                    // Update UI with account details
                    accountNameTextView.setText(foodDetail.getAccount().getName());

                    // Check if account image is available, otherwise load default avatar
                    String accountImageUrl = foodDetail.getAccount().getImg();
                    if (accountImageUrl != null && !accountImageUrl.isEmpty()) {
                        Glide.with(FoodDetailActivity.this)
                                .load(accountImageUrl)
                                .into(accountImageView);
                    } else {
                        // Load default avatar
                        Glide.with(FoodDetailActivity.this)
                                .load(R.drawable.baseline_account_circle_24) // Ensure this drawable exists in your resources
                                .into(accountImageView);
                    }

                } else {
                    Log.e(TAG, "Response unsuccessful or body is null. Code: " + response.code());
                    Toast.makeText(FoodDetailActivity.this, "Failed to load food details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FoodDto> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCommentsByFoodId(String foodId) {
        ApiService apiService = RetrofitUtility.getClient().create(ApiService.class);
        Call<List<CommentDto>> call = apiService.getCommentsByFoodId(foodId);

        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CommentDto> commentList = response.body();
                    commentAdapter = new CommentAdapter(FoodDetailActivity.this, commentList);
                    commentsRecyclerView.setAdapter(commentAdapter);
                } else {
                    Toast.makeText(FoodDetailActivity.this, "No comments found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CommentDto>> call, Throwable t) {
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
