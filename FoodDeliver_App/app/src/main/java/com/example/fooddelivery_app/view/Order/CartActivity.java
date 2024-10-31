package com.example.fooddelivery_app.view.Order;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.CartAdapter;
import com.example.fooddelivery_app.model.Order.Cart;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.viewmodel.Order.CartListViewModel;
import com.example.fooddelivery_app.viewmodel.Order.OrderListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        CartListViewModel cartViewModel = new ViewModelProvider(this).get(CartListViewModel.class);

        UUID userId = UUID.fromString(cartViewModel.getUserIdFromToken(this));
//        String url=cartViewModel.getUserIdFromToken(this);
//        UUID userId = UUID.fromString("60396245-056f-40be-3d0d-08dcf8f46b6c");
//        String finalUrl = url;
        cartViewModel.getCartByUserId(userId).
                observe(this, carts -> {
            TextView totalPrice=findViewById(R.id.totalPrice);
            if (carts != null) {
                //totalPrice.setText(finalUrl +" "+carts.size());
                    CartAdapter cartAdapter = new CartAdapter(carts);
                    recyclerView.setAdapter(cartAdapter);
            }else{
                totalPrice.setText("null");
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_cart:
                    // Open CartActivity when Cart menu item is clicked
                    Intent cartIntent = new Intent(this, CartActivity.class);
                    startActivity(cartIntent);
                    finish();
                    return true;
                case R.id.navigation_More:
                    // Open CartActivity when Cart menu item is clicked
                    Intent orderIntent = new Intent(this, OrderListActivity.class);
                    startActivity(orderIntent);
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
}