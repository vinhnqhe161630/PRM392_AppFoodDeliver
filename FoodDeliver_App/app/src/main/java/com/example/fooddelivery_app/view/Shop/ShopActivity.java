package com.example.fooddelivery_app.view.Shop;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.adapter.ShopAdapter;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.viewmodel.Shop.ShopViewModel;

public class ShopActivity extends AppCompatActivity {
    private Shop shopViewModel;
    private ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewShops);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        shopAdapter = new ShopAdapter();
        recyclerView.setAdapter(shopAdapter);

        ShopViewModel shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);

        shopViewModel.getAllShops().observe(this, shops -> {
            // Update the adapter with the new list of shops
            shopAdapter.setShopList(shops); // Updated method name here
        });

        // Optional: Observe error messages
        shopViewModel.getErrorMessages().observe(this, errorMessage -> {
            if (errorMessage != null) {
                // Display error message to the user, e.g., using a Toast or Snackbar
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Optional: If you want to refresh the shop list when the activity is created
        shopViewModel.refreshShops();
    }
}