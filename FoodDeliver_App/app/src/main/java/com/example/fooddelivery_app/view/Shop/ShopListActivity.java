package com.example.fooddelivery_app.view.Shop;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.view.MainActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.example.fooddelivery_app.viewmodel.Shop.ShopListViewModel;
import com.example.fooddelivery_app.viewmodel.Shop.ShopVotedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShopListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);

        RecyclerView shoprecyclerView = findViewById(R.id.productRecyclerView2);
        shoprecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Khởi tạo và thiết lập Adapter cho RecyclerView
        ShopAdapter shopAdapter = new ShopAdapter();


        ShopListViewModel shopViewModel = new ViewModelProvider(this).get(ShopListViewModel.class);

        shopViewModel.getAllShops().observe(this, shops -> {
            shopAdapter.setShopList(this,shops);
        });

        // Set adapters to the RecyclerViews
        shoprecyclerView.setAdapter(shopAdapter);

        // Setup SearchView listener
        SearchView searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                shopViewModel.searchShops(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                shopViewModel.searchShops(newText);
                return true;
            }
        });
//
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_shop);
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
                // Handle other menu items here

            }
        });
    }
}
