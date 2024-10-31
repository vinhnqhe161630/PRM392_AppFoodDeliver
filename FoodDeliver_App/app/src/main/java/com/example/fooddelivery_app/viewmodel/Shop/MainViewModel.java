package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Food.Food;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.FoodRepository;
import com.example.fooddelivery_app.repository.ShopRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MainViewModel extends ViewModel {
    private final ShopRepository shopRepository;
    private final FoodRepository foodRepository;

    private final MutableLiveData<List<Shop>> allShops;
    private final MutableLiveData<List<FoodDto>> allFoods;

    public MainViewModel() {
        shopRepository = new ShopRepository();
        foodRepository = new FoodRepository();


        allShops = shopRepository.getAllShops();
        allFoods = foodRepository.getFoods();

    }

    public MutableLiveData<List<Shop>> getAllShops() {
        return allShops;

    }
    public MutableLiveData<List<FoodDto>> getFoods() {
        return allFoods;
    }

    public void refreshShops() {
        // Re-fetch the data from the repository
        MutableLiveData<List<Shop>> refreshedShops = shopRepository.getAllShops();

        refreshedShops.observeForever(updatedShops -> {
            allShops.setValue(updatedShops);
        });

        MutableLiveData<List<FoodDto>> refreshedFoods = foodRepository.getFoods();
        refreshedFoods.observeForever(updatedFoods -> {
            allFoods.setValue(updatedFoods);
        });
    }

    public void filterShops(String query) {
        if (query.isEmpty()) {
            refreshShops();
        } else {
            List<Shop> filteredShops = allShops.getValue().stream()
                    .filter(shop -> shop.getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            allShops.setValue(filteredShops);
        }
    }

    public void filterFoods(String query) {
        if (query.isEmpty()) {
            refreshShops();
        } else {
            List<FoodDto> filteredFoods = allFoods.getValue().stream()
                    .filter(food -> food.getName().toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            allFoods.setValue(filteredFoods);
        }
    }


}
