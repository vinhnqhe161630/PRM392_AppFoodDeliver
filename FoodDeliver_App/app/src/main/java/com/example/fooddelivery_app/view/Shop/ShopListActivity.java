package com.example.fooddelivery_app.view.Shop;

import android.os.Bundle;

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
    }
}
