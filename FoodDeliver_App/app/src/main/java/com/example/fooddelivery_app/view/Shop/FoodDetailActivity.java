package com.example.fooddelivery_app.view.Shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.CommentAdapter;
import com.example.fooddelivery_app.model.Comment.AddCommentDto;
import com.example.fooddelivery_app.model.Comment.CommentDto;
import com.example.fooddelivery_app.model.Comment.PurchaseCheckDto;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.FoodApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodDetailActivity extends AppCompatActivity {

    private static final String TAG = "FoodDetailActivity";
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView;
    private TextView accountNameTextView, accountAddressTextView, tvFoodQuantity;;
    private ImageView productImageView;
    private CommentAdapter commentAdapter;
    private RecyclerView commentsRecyclerView;
    private RatingBar ratingBar;
    private EditText commentInput;
    private RatingBar commentRatingBar;
    private Button submitCommentButton;
    CardView cardShop;
    private boolean hasUserCommented = false , hasPurchased = false;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        // Initialize UI components
        productNameTextView = findViewById(R.id.productDetailTitle);
        productImageView = findViewById(R.id.productImageView);
        productDescriptionTextView = findViewById(R.id.productDetailDescription);
        productPriceTextView = findViewById(R.id.productPriceTextView);

        // New components for account details
        accountNameTextView = findViewById(R.id.ShopNameTextView);
        accountAddressTextView = findViewById(R.id.ShopAddress);

        cardShop = findViewById(R.id.cardShop);
        tvFoodQuantity = findViewById(R.id.tvFoodQuantity);
        // Initialize RecyclerView for comments

        commentInput = findViewById(R.id.commentInput);
        commentRatingBar = findViewById(R.id.commentRatingBar);
        submitCommentButton = findViewById(R.id.submitCommentButton);
        commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String userId = getUserIdFromToken(FoodDetailActivity.this);
        // Get the foodId from the Intent
        String foodId = getIntent().getStringExtra("foodId");
        if (foodId != null) {
            getFoodDetail(foodId);
            getFoodQuantity(foodId);
            getCommentsByFoodId(foodId);
            checkPurchase(userId, foodId);
        } else {
            Toast.makeText(this, "Food ID is missing", Toast.LENGTH_SHORT).show();
            finish();
        }



        submitCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentContent = commentInput.getText().toString().trim();
                float rating = commentRatingBar.getRating();
                String authToken = getIntent().getStringExtra("auth_token");

                    String userId = getUserIdFromToken(FoodDetailActivity.this);



                if (commentContent.isEmpty()) {
                    Toast.makeText(FoodDetailActivity.this, "Comment cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else {
                    addComment(commentContent, rating, userId, foodId);
                }
            }
        });
    }

    private void getFoodQuantity(String foodId) {
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<Integer> call = apiService.getFoodQuantity(foodId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int quantity = response.body();
                    Log.d(TAG, "Food Quantity: " + quantity);
                    tvFoodQuantity.setText("Đã bán: " + quantity);
                } else {
                    Log.e(TAG, "Failed to retrieve quantity. Code: " + response.code());
                    Toast.makeText(FoodDetailActivity.this, "Failed to load quantity", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
        }}
    private void getFoodDetail(String foodId) {
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
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
                    accountAddressTextView.setText(foodDetail.getAccount().getAddress());


                    ratingBar = findViewById(R.id.ratingBar1);

                    // Get the average rating from your foodDetail object
                    float averageRating = (float) foodDetail.calculateAverageRating(); // Make sure this method returns a float

                    // Set the RatingBar to the average rating
                    ratingBar.setRating(averageRating);



                    cardShop.setOnClickListener(v -> {
                        Intent intent = new Intent(FoodDetailActivity.this, ShopDetailActivity.class);
                        intent.putExtra("ShopId", foodDetail.getAccount().getId().toString());
                        startActivity(intent);
                    });


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
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<List<CommentDto>> call = apiService.getCommentsByFoodId(foodId);

        call.enqueue(new Callback<List<CommentDto>>() {
            @Override
            public void onResponse(Call<List<CommentDto>> call, Response<List<CommentDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CommentDto> commentList = response.body();
                    commentAdapter = new CommentAdapter(FoodDetailActivity.this, commentList);
                    commentsRecyclerView.setAdapter(commentAdapter);


                    // Check if the user has already commented
                    String userId = getUserIdFromToken(FoodDetailActivity.this);
                    for (CommentDto comment : commentList) {
                        if (comment.getUserId().equalsIgnoreCase(userId)) {
                            hasUserCommented = true;
                            break; // Exit loop if we found a comment by the user
                        }
                    }

                    // Hide the comment input and rating bar if the user has commented
                    if (hasUserCommented || !hasPurchased) {
                        View commentInputLayout = findViewById(R.id.commentInputLayout); // Assuming you have a layout for comment input
                        commentInputLayout.setVisibility(View.GONE);

                    }
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
    private void checkPurchase(String userId, String foodId) {
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<PurchaseCheckDto> call = apiService.checkPurchase(userId, foodId);

        call.enqueue(new Callback<PurchaseCheckDto>() {
            @Override
            public void onResponse(Call<PurchaseCheckDto> call, Response<PurchaseCheckDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    hasPurchased = response.body().isHasPurchased();
                } else {
                    Log.e(TAG, "Purchase check failed with code: " + response.code());
                    Toast.makeText(FoodDetailActivity.this, "Failed to check purchase status", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PurchaseCheckDto> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Method to add a new comment
    private void addComment(String content, float rating, String userId, String foodId) {
        FoodApiService foodApiService = RetrofitUtility.getClient().create(FoodApiService.class);
        AddCommentDto addCommentDto = new AddCommentDto();
        addCommentDto.setContent(content);
        addCommentDto.setVote((int) rating);
        addCommentDto.setUserId(userId);
        addCommentDto.setFoodId(foodId);

        String currentDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault()).format(new Date());
        addCommentDto.setCommentDate(currentDate);
        Call<AddCommentDto> call = foodApiService.addComment(addCommentDto);

        call.enqueue(new Callback<AddCommentDto>() {
            @Override
            public void onResponse(Call<AddCommentDto> call, Response<AddCommentDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(FoodDetailActivity.this, "Comment added successfully", Toast.LENGTH_SHORT).show();
                    commentInput.setText(""); // Clear the input field
                    commentRatingBar.setRating(0); // Reset rating bar
                    getCommentsByFoodId(foodId); // Refresh the comments list
                } else {
                    Log.e(TAG, "Failed to add comment. Code: " + response.code());
                    Toast.makeText(FoodDetailActivity.this, "Failed to add comment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentDto> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteComment(String commentId, int position) {
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);
        Call<Void> call = apiService.deleteComment(commentId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FoodDetailActivity.this, "Comment deleted successfully", Toast.LENGTH_SHORT).show();
                    commentAdapter.notifyItemRemoved(position);
                    commentAdapter.notifyDataSetChanged(); // Optional: to refresh the list
                } else {
                    Toast.makeText(FoodDetailActivity.this, "Failed to delete comment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FoodDetailActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
