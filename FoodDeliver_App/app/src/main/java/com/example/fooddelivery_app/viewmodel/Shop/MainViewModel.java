package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Food.Food;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.FoodRepository;
import com.example.fooddelivery_app.repository.ShopRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final ShopRepository shopRepository;
    private final FoodRepository foodRepository;

    private final MutableLiveData<List<Shop>> allShops;
    private final MutableLiveData<List<Food>> allFoods;

    public MainViewModel() {
        shopRepository = new ShopRepository();
        foodRepository = new FoodRepository();

        allShops = shopRepository.getAllShops();
        allFoods = foodRepository.getFoods();

    }

    public MutableLiveData<List<Shop>> getAllShops() {
        return allShops;

    }
    public MutableLiveData<List<Food>> getFoods() {
        return allFoods;
    }

    public void refreshShops() {
        // Re-fetch the data from the repository
        MutableLiveData<List<Shop>> refreshedShops = shopRepository.getAllShops();
        refreshedShops.observeForever(updatedShops -> {
            allShops.setValue(updatedShops);
        });

        MutableLiveData<List<Food>> refreshedFoods = foodRepository.getFoods();
        refreshedFoods.observeForever(updatedFoods -> {
            allFoods.setValue(updatedFoods);
        });
    }
}
