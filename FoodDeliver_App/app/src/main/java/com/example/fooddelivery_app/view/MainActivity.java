package com.example.fooddelivery_app.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.widget.SearchView;
import com.example.fooddelivery_app.R;

import com.example.fooddelivery_app.adapter.FoodAdapter;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.view.Auth.LoginActivity;
import com.example.fooddelivery_app.view.Order.CartActivity;
import com.example.fooddelivery_app.view.Order.OrderListActivity;
import com.example.fooddelivery_app.view.Shop.BlogActivity;
import com.example.fooddelivery_app.view.Shop.FoodDetailActivity;
import com.example.fooddelivery_app.view.Shop.ShopDetailActivity;
import com.example.fooddelivery_app.view.Shop.ShopListActivity;
import com.example.fooddelivery_app.view.Shop.ShopVotedActivity;
import com.example.fooddelivery_app.viewmodel.Shop.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView see1,see2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView shopsRecyclerView = findViewById(R.id.productRecyclerView2);
        RecyclerView foodsRecyclerView = findViewById(R.id.productRecyclerView3);

        SearchView searchView = findViewById(R.id.searchView);

        // Set up layout managers for the RecyclerViews
        shopsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        foodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShopAdapter shopsAdapter = new ShopAdapter();
        FoodAdapter foodsAdapter = new FoodAdapter(this, new ArrayList<>());


        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Observe the shops LiveData to update the shopsAdapter
        mainViewModel.getAllShops().observe(this, shopsList -> {
            shopsAdapter.setShopList(this,shopsList);
            shopsRecyclerView.setAdapter(shopsAdapter);
        });
        // Observe the foods LiveData to update the foodsAdapter
        mainViewModel.getFoods().observe(this, foodsList -> {
            foodsAdapter.setFoodList(this,foodsList);
            foodsRecyclerView.setAdapter(foodsAdapter);
        });

//
        mainViewModel.refreshShops();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the adapters based on the new text
                mainViewModel.filterShops(newText); // Filter shops
                mainViewModel.filterFoods(newText); // Filter foods

                return true; // Return true if the query has been handled
            }
        });

        see1=findViewById(R.id.see1);
        see1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
            startActivity(intent);
        });
        see2=findViewById(R.id.see2);
        see2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShopListActivity.class);
            startActivity(intent);
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        // Set a listener for menu item clicks
        // NavigationBarView. setOnItemSelectedListener
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