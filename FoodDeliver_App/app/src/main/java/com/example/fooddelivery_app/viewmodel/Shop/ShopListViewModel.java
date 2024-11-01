package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.ShopRepository;

import java.util.List;

public class ShopListViewModel extends ViewModel {

    private final ShopRepository shopRepository;
    private final MutableLiveData<List<Shop>> allShops;

    public ShopListViewModel() {
        shopRepository = new ShopRepository();
        allShops = shopRepository.getAllShops();

    }

    public LiveData<List<Shop>> getAllShops() {
        return allShops;
    }

    public void refreshShops() {
        // Re-fetch the data from the repository
        MutableLiveData<List<Shop>> refreshedShops = shopRepository.getAllShops();
        refreshedShops.observeForever(updatedShops -> {
            allShops.setValue(updatedShops);
        });
    }


}
