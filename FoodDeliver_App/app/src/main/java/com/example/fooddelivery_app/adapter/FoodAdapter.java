package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.FoodApiService;
import com.example.fooddelivery_app.view.Shop.FoodDetailActivity;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<FoodDto> foodList;

    public FoodAdapter(Context context, List<FoodDto> foodList) {
        this.context = context;
        this.foodList = foodList;
    }
    public void setFoodList(Context context,List<FoodDto> foodList) {
        this.foodList = foodList;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodDto food = foodList.get(position);
        holder.productNameTextView.setText(food.getName());
        holder.productInfoTextView.setText(food.getDescription());
        holder.productPriceTextView.setText(String.format("$%.2f", food.getPrice()));
        fetchAndDisplayFoodQuantity(food.getId().toString(), holder.productQuantityTextView);
        // Load image using Glide
        Glide.with(context).load(food.getImg()).into(holder.productImageView);
        holder.cardproduct.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity.class);
            intent.putExtra("foodId", food.getId().toString());
            context.startActivity(intent);
        });

        float averageRating = (float) food.calculateAverageRating();
        holder.ratingBar1.setRating(averageRating); // Set the rating bar stars to the average rating
    }
    private void fetchAndDisplayFoodQuantity(String foodId, TextView quantityTextView) {
        // Initialize the API service
        FoodApiService apiService = RetrofitUtility.getClient().create(FoodApiService.class);

        // Call the API to get the quantity
        Call<Integer> call = apiService.getFoodQuantity(foodId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int quantity = response.body();
                    quantityTextView.setText("Đã bán: " + quantity);
                } else {
                    quantityTextView.setText("Đã bán: N/A"); // Handle case where quantity isn't available
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                quantityTextView.setText("Đã bán: N/A"); // Display an error message if call fails
            }
        });
    }
    public void sortFoodListByCommentCount() {
        Collections.sort(foodList, (food1, food2) -> Integer.compare(food2.getCommentCount(), food1.getCommentCount()));
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView productNameTextView;
        TextView productInfoTextView;
        TextView productPriceTextView;
        ImageView productImageView;
        RatingBar ratingBar1;
        TextView productQuantityTextView;
        CardView cardproduct;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productInfoTextView = itemView.findViewById(R.id.productInfoTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            ratingBar1 = itemView.findViewById(R.id.ratingBar1);
            cardproduct = itemView.findViewById(R.id.cardproduct);
            productQuantityTextView = itemView.findViewById(R.id.productQuantityTextView);



        }
    }
}
