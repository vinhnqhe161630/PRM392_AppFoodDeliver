package com.example.fooddelivery_app.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Order.Cart;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Shop.FoodDetailActivity;
import com.example.fooddelivery_app.viewmodel.Order.CartListViewModel;

import java.util.List;
import java.util.UUID;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private ViewModelStoreOwner viewModelStoreOwner;
    private Context context;
    public CartAdapter(List<Cart> cartList, ViewModelStoreOwner viewModelStoreOwner,Context context) {
        this.cartList = cartList;
        this.viewModelStoreOwner = viewModelStoreOwner;
        this.context=context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productcart, parent, false);
        return new CartViewHolder(view, viewModelStoreOwner);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.cartView=cart;
        holder.cartItemName.setText(cart.getFoodName());
        holder.cartItemPrice.setText(cart.getPrice()+" VND");
        holder.quantityTextView.setText(cart.getQuantity()+" ");
        Glide.with(context).load(cart.getFoodImage()).into(holder.productImageView);
        // Set other cart item details here
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView cartItemName;
        TextView cartItemPrice;
        TextView quantityTextView;
        Button increase;
        Button decrease;
        ImageView delete;
        Cart cartView;
        ImageView productImageView;

        public CartViewHolder(@NonNull View itemView, ViewModelStoreOwner viewModelStoreOwner) {
            super(itemView);
            cartItemName = itemView.findViewById(R.id.tvProductName);
            cartItemPrice = itemView.findViewById(R.id.productPriceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            increase = itemView.findViewById(R.id.buttonIncrease);
            decrease = itemView.findViewById(R.id.buttonDecrease);
            delete = itemView.findViewById(R.id.imgRemove);
            productImageView=itemView.findViewById(R.id.imgShop);
            increase.setOnClickListener(v -> {
                CartListViewModel cartViewModel = new ViewModelProvider(viewModelStoreOwner).get(CartListViewModel.class);

                cartViewModel.increaseCart(cartView.getId()).observe((LifecycleOwner) viewModelStoreOwner, message -> {

                        Intent intent = new Intent(v.getContext(), CartActivity.class);
                        v.getContext().startActivity(intent);

                });
            });
            decrease.setOnClickListener(v -> {
                CartListViewModel cartViewModel = new ViewModelProvider(viewModelStoreOwner).get(CartListViewModel.class);

                cartViewModel.decreaseCart(cartView.getId()).observe((LifecycleOwner) viewModelStoreOwner, message -> {

                        Intent intent = new Intent(v.getContext(), CartActivity.class);
                        v.getContext().startActivity(intent);

                });
            });
            delete.setOnClickListener(v -> {
                CartListViewModel cartViewModel = new ViewModelProvider(viewModelStoreOwner).get(CartListViewModel.class);

                cartViewModel.deleteCart(cartView.getId()).observe((LifecycleOwner) viewModelStoreOwner, message -> {

                        Intent intent = new Intent(v.getContext(), CartActivity.class);
                        v.getContext().startActivity(intent);

                });
            });
        }
    }

}

