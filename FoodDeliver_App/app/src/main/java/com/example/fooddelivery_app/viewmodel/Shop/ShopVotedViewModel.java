package com.example.fooddelivery_app.viewmodel.Shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.repository.ShopRepository;


import java.util.List;
import java.util.stream.Collectors;

public class ShopVotedViewModel extends ViewModel {
    private final ShopRepository shopRepository;

    private final MutableLiveData<List<Shop>> allShops;
    private final MutableLiveData<List<Shop>> goodShops;
    private final MutableLiveData<List<Shop>> badShops;



    public ShopVotedViewModel() {
        shopRepository = new ShopRepository();
        allShops = shopRepository.getAllShops();
        goodShops = new MutableLiveData<>();
        badShops = new MutableLiveData<>();

        allShops.observeForever(shops -> {
            updateGoodAndBadShops(shops);
        });
    }


    public LiveData<List<Shop>> getAllShops() {
        return allShops;
    }
    public LiveData<List<Shop>> getGoodShops() {
        return goodShops;
    }

    public LiveData<List<Shop>> getBadShops() {
        return badShops;
    }




    public void refreshShops() {
        // Re-fetch the data from the repository
        MutableLiveData<List<Shop>> refreshedShops = shopRepository.getAllShops();
        refreshedShops.observeForever(updatedShops -> {
            allShops.setValue(updatedShops);
            updateGoodAndBadShops(updatedShops);
        });
    }

    // Method to update goodShops and badShops based on votes
    private void updateGoodAndBadShops(List<Shop> shops) {
        // Filter good shops with vote > 4, limited to top 10
        List<Shop> topGoodShops = shops.stream()
                .filter(shop -> shop.getVote() > 4)
                .sorted((s1, s2) -> Double.compare(s2.getVote(), s1.getVote()))
                .limit(10)
                .collect(Collectors.toList());
        goodShops.setValue(topGoodShops);

        // Filter bad shops with vote < 3, limited to top 10
        List<Shop> topBadShops = shops.stream()
                .filter(shop -> shop.getVote() > 0 && shop.getVote() < 3)
                .sorted((s1, s2) -> Double.compare(s1.getVote(), s2.getVote()))
                .limit(10)
                .collect(Collectors.toList());
        badShops.setValue(topBadShops);
    }
}
