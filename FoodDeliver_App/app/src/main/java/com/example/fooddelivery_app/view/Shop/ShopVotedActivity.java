package com.example.fooddelivery_app.view.Shop;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.viewmodel.Shop.ShopVotedViewModel;

public class ShopVotedActivity extends AppCompatActivity {
    private Shop shopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shopvoted);

        RecyclerView listBest = findViewById(R.id.listBest);
        RecyclerView listWorst = findViewById(R.id.listWorst);

        // Set up layout managers for the RecyclerViews
        listBest.setLayoutManager(new LinearLayoutManager(this));
        listWorst.setLayoutManager(new LinearLayoutManager(this));


        // Initialize the adapters
        ShopAdapter bestShopAdapter = new ShopAdapter();
        ShopAdapter worstShopAdapter = new ShopAdapter();



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
    }
}