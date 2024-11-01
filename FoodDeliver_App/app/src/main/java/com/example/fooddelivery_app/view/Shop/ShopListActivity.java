package com.example.fooddelivery_app.view.Shop;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.viewmodel.Shop.ShopListViewModel;
import com.example.fooddelivery_app.viewmodel.Shop.ShopVotedViewModel;

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

        shopViewModel.getAllShops().observe(this, goodShops -> {
            shopAdapter.setShopList(this,goodShops);
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

        findViewById(R.id.filter_best_seller).setOnClickListener(v -> shopViewModel.filterBestSellers());
        findViewById(R.id.filter_high_rating).setOnClickListener(v -> shopViewModel.filterHighRating());
        findViewById(R.id.filter_low_to_high).setOnClickListener(v -> shopViewModel.filterLowToHighPrice());
        findViewById(R.id.filter_high_to_low).setOnClickListener(v -> shopViewModel.filterHighToLowPrice());
    }
}
