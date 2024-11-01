package com.example.fooddelivery_app.view.Shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.BestAdapter;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.adapter.WorstAdapter;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.example.fooddelivery_app.viewmodel.Shop.ShopVotedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShopVotedActivity extends AppCompatActivity {
    private Shop shopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopvoted);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_Rank);
        RecyclerView listBest = findViewById(R.id.listBest);
        RecyclerView listWorst = findViewById(R.id.listWorst);

        // Set up layout managers for the RecyclerViews
        listBest.setLayoutManager(new LinearLayoutManager(this));
        listWorst.setLayoutManager(new LinearLayoutManager(this));


        // Initialize the adapters
        BestAdapter bestShopAdapter = new BestAdapter();
        WorstAdapter worstShopAdapter = new WorstAdapter();



        ShopVotedViewModel shopViewModel = new ViewModelProvider(this).get(ShopVotedViewModel.class);

        // Observe the goodShops LiveData to update the bestShopAdapter
        shopViewModel.getGoodShops().observe(this, goodShops -> {
            bestShopAdapter.setShopList(this,goodShops);
        });

        // Observe the badShops LiveData to update the worstShopAdapter
        shopViewModel.getBadShops().observe(this, badShops -> {
            worstShopAdapter.setShopList(this,badShops);
        });

        // Set adapters to the RecyclerViews
        listBest.setAdapter(bestShopAdapter);
        listWorst.setAdapter(worstShopAdapter);

        // Optional: If you want to refresh the shop list when the activity is created
        shopViewModel.refreshShops();
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
                case R.id.navigation_shop:
                    // Open CartActivity when Cart menu item is clicked
                    Intent shopIntent = new Intent(this, ShopDetailActivity.class);
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