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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.view.Shop.FoodDetailActivity;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<FoodDto> foodList;

    public FoodAdapter(Context context, List<FoodDto> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodDto food = foodList.get(position);
        holder.productNameTextView.setText(food.getName());
        holder.productInfoTextView.setText(food.getDescription());
        holder.productPriceTextView.setText(String.format("$%.2f", food.getPrice()));

        // Load image using Glide
        Glide.with(context).load(food.getImg()).into(holder.productImageView);
        holder.productImageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity.class);
            intent.putExtra("foodId", food.getId().toString());
            context.startActivity(intent);
        });

        float averageRating = (float) food.calculateAverageRating();
        holder.ratingBar1.setRating(averageRating); // Set the rating bar stars to the average rating
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
        Button deleteButton;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productInfoTextView = itemView.findViewById(R.id.productInfoTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
            ratingBar1 = itemView.findViewById(R.id.ratingBar1);
            deleteButton = itemView.findViewById(R.id.deleteButton);

            // Optional: set delete button click listener
            deleteButton.setOnClickListener(v -> {
                // Handle delete action (e.g., remove item from list and notify adapter)
            });
        }
    }
}
