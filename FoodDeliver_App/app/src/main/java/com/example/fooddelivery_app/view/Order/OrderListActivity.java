package com.example.fooddelivery_app.view.Order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.OrderAdapter;
import com.example.fooddelivery_app.model.Order.Order;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Shop.BlogActivity;
import com.example.fooddelivery_app.view.Shop.ShopDetailActivity;
import com.example.fooddelivery_app.view.Shop.ShopListActivity;
import com.example.fooddelivery_app.view.Shop.ShopVotedActivity;
import com.example.fooddelivery_app.viewmodel.Order.OrderListViewModel;
import com.example.fooddelivery_app.viewmodel.Order.OrderListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.UUID;

public class OrderListActivity extends AppCompatActivity {
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_list);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_More);
        RecyclerView recyclerView = findViewById(R.id.listView);
        OrderListViewModel orderViewModel = new ViewModelProvider(this).get(OrderListViewModel.class);

        UUID userId = UUID.fromString(orderViewModel.getUserIdFromToken(this));
//        String url=orderViewModel.getUserIdFromToken(this);
//        UUID userId = UUID.fromString("60396245-056f-40be-3d0d-08dcf8f46b6c");
//        String finalUrl = url;
        orderViewModel.getOrderByUserId(userId).
                observe(this, orders -> {
                    //TextView totalPrice=findViewById(R.id.tvTotalAmount);
                    if (orders != null) {
                        //totalPrice.setText(finalUrl +" "+orders.size());
                        OrderAdapter orderAdapter = new OrderAdapter(orders,this);
                        recyclerView.setAdapter(orderAdapter);
                    }else{
                        //totalPrice.setText("null");
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
}