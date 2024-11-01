package com.example.fooddelivery_app.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.model.Order.Cart;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Shop.FoodDetailActivity;
import com.example.fooddelivery_app.viewmodel.Order.CartListViewModel;

import java.util.List;
import java.util.UUID;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart> cartList;

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productcart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.cartView=cart;
        holder.cartItemName.setText(cart.getFoodName());
        holder.cartItemPrice.setText(cart.getPrice()+" VND");
        holder.quantityTextView.setText(cart.getQuantity()+" ");
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
        Button delete;
        Cart cartView;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemName = itemView.findViewById(R.id.tvProductName);
            cartItemPrice = itemView.findViewById(R.id.productPriceTextView);
            quantityTextView= itemView.findViewById(R.id.quantityTextView);
            increase=itemView.findViewById(R.id.buttonIncrease);
            decrease=itemView.findViewById(R.id.buttonDecrease);
            delete=itemView.findViewById(R.id.deleteButton);
            increase.setOnClickListener(v -> {
                // Ensure the context is an instance of ViewModelStoreOwner
                if (v.getContext() instanceof ViewModelStoreOwner) {
                    ViewModelStoreOwner viewModelStoreOwner = (ViewModelStoreOwner) v.getContext();
                    CartListViewModel cartViewModel = new ViewModelProvider(viewModelStoreOwner).get(CartListViewModel.class);

                    cartViewModel.increaseCart(cartView.getId()).observe((LifecycleOwner) viewModelStoreOwner, message -> {
                        if (message != null) {
                            Intent intent = new Intent(v.getContext(), CartActivity.class);
                            v.getContext().startActivity(intent);
                        } else {
                            Toast.makeText(v.getContext(), "Add to cart failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(v.getContext(), "Context is not a ViewModelStoreOwner", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
