package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.ShopRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShopListViewModel extends ViewModel {

    private final ShopRepository shopRepository;
    private final MutableLiveData<List<Shop>> allShops;
    private List<Shop> originalShops;  // Store original list for reference

    public ShopListViewModel() {
        shopRepository = new ShopRepository();
        allShops = new MutableLiveData<>();
        loadAllShops();
    }

    private void loadAllShops() {
        shopRepository.getAllShops().observeForever(shops -> {
            originalShops = shops;  // Store the original list
            allShops.setValue(shops);
        });
    }

    public LiveData<List<Shop>> getAllShops() {
        return allShops;
    }

    public void searchShops(String query) {
        if (query == null || query.isEmpty()) {
            // Reset to the original list if query is empty
            allShops.setValue(originalShops);
            return;
        }

        List<Shop> filteredShops = new ArrayList<>();
        for (Shop shop : originalShops) {
            if (shop.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredShops.add(shop);
            }
        }
        allShops.setValue(filteredShops);
    }

    public void refreshShops() {
        loadAllShops();  // Re-fetch the data from the repository
    }



}
