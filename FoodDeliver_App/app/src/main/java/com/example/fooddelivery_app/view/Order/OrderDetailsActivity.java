package com.example.fooddelivery_app.view.Order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.OrderAdapter;
import com.example.fooddelivery_app.adapter.OrderDetailsAdapter;
import com.example.fooddelivery_app.model.Order.OrderDetails;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Shop.ShopDetailActivity;
import com.example.fooddelivery_app.view.Shop.ShopListActivity;
import com.example.fooddelivery_app.view.Shop.ShopVotedActivity;
import com.example.fooddelivery_app.viewmodel.Order.OrderListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.UUID;
public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orderdetails);
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        OrderListViewModel orderViewModel = new ViewModelProvider(this).get(OrderListViewModel.class);

        // Retrieve the orderId from the intent
        Intent intent = getIntent();
        String orderIdString = intent.getStringExtra("ORDER_ID");
        UUID orderId = UUID.fromString(orderIdString);
        //UUID orderId = UUID.fromString("256658e3-798d-403d-af53-08dcfa6a0139");

        orderViewModel.getOrderDetailsByUserId(orderId,this).observe(this, orders -> {
            TextView totalPrice = findViewById(R.id.totalPrice);

            if (orders != null) {
                int total = 0;
                for (OrderDetails order : orders) {
                    total += order.getPrice() * order.getQuantity();
                }

                // Set the total price to the TextView
                totalPrice.setText("Tổng Thanh Toán :"+total+" VND");
                OrderDetailsAdapter orderAdapter = new OrderDetailsAdapter(orders,this);
                recyclerView.setAdapter(orderAdapter);

            } else {
                totalPrice.setText("null"+orderIdString);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_More);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_cart:
                    Intent cartIntent = new Intent(this, CartActivity.class);
                    startActivity(cartIntent);
                    finish();
                    return true;
                case R.id.navigation_More:
                    Intent orderIntent = new Intent(this, OrderListActivity.class);
                    startActivity(orderIntent);
                    finish();
                    return true;
                case R.id.navigation_shop:
                    // Open CartActivity when Cart menu item is clicked
                    Intent shopIntent = new Intent(this, ShopListActivity.class);
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
            }
        });
    }
}
