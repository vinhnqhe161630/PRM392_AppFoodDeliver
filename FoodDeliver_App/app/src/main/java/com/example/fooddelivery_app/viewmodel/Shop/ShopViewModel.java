package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.Shop.ShopRepository;
import com.example.fooddelivery_app.retrofit.apis.ShopApiService;

import java.util.List;

public class ShopViewModel extends ViewModel {

    private MutableLiveData<List<ShopViewModel>> shopLiveData = new MutableLiveData<>();
    private ShopRepository shopRepository;

    public ShopViewModel() {
        shopRepository = new ShopRepository();
    }


}
