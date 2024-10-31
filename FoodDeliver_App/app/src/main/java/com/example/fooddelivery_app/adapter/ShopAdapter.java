package com.example.fooddelivery_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.view.FoodDetailActivity;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {
    private List<Shop> shopList;
    private Context context;
    public void setShopList(Context context,List<Shop> shopList) {
        this.shopList = shopList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop = shopList.get(position);

        // Bind data to the views
        holder.tvShopName.setText(shop.getName());
        holder.tvTotalOrder.setText("Total Orders: " + shop.getTotalOrder());

        // Set rating to the RatingBar
        holder.ratingBar.setRating((float)shop.getVote());

        Glide.with(context).load(shop.getImg()).into(holder.imgShop);
        holder.imgShop.setOnClickListener(v -> {
            Intent intent = new Intent(context, FoodDetailActivity.class);
            intent.putExtra("foodId", shop.getId().toString());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return shopList != null ? shopList.size() : 0;
    }
    static class ShopViewHolder extends RecyclerView.ViewHolder {
        TextView tvShopName, tvTotalOrder;
        RatingBar ratingBar;
        ImageView imgShop;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvShopName = itemView.findViewById(R.id.tvShopName);
            tvTotalOrder = itemView.findViewById(R.id.tvTotalOrder);
            ratingBar = itemView.findViewById(R.id.tvVote);
            imgShop = itemView.findViewById(R.id.imgShop); // Updated to match RatingBar ID
        }
    }
}
