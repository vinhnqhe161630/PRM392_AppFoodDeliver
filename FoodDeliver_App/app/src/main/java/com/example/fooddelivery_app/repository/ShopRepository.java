package com.example.fooddelivery_app.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Shop.Shop;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.ShopApiService;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopRepository {
    private ShopApiService shopApiService;
    public  ShopRepository(){
        shopApiService = RetrofitUtility.getClient().create(ShopApiService.class);
    }

    public MutableLiveData<List<Shop>> getAllShops() {
        MutableLiveData<List<Shop>> liveData = new MutableLiveData<>();
        Call<List<Shop>> call = shopApiService.getAllShops();

        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, retrofit2.Response<List<Shop>> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                // Xử lý khi có lỗi không kết nối được tới API
                liveData.postValue(null);  // Hoặc gửi thông báo lỗi
                t.printStackTrace(); // Hoặc log lỗi, hoặc xử lý thêm
            }
        });

        return liveData;
    }
    public MutableLiveData<List<Shop>> getTop5Shops() {
        MutableLiveData<List<Shop>> liveData = new MutableLiveData<>();
        Call<List<Shop>> call = shopApiService.getAllShops();

        call.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, retrofit2.Response<List<Shop>> response) {
                if (response.isSuccessful()) {
                    List<Shop> allShops = response.body();
                    if (allShops != null) {
                        // Lấy 5 cửa hàng đầu tiên, hoặc toàn bộ danh sách nếu ít hơn 5
                        List<Shop> top5Shops = allShops.size() > 5 ? allShops.subList(0, 5) : allShops;
                        liveData.postValue(top5Shops);
                    } else {
                        liveData.postValue(new ArrayList<>()); // Nếu response trả về null, trả về danh sách trống
                    }
                } else {
                    liveData.postValue(null); // Xử lý khi response không thành công
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                liveData.postValue(null); // Xử lý khi có lỗi không kết nối được tới API
                t.printStackTrace(); // Hoặc log lỗi, hoặc xử lý thêm
            }
        });

        return liveData;
    }

    private void handleErrorResponse(Response<ResponseBody> response, MutableLiveData<String> liveData) {
        try {
            String responseBody = response.errorBody().string();
            JSONObject jsonObject = new JSONObject(responseBody);
            String message = jsonObject.getString("message");
            liveData.postValue("Fail: " + message);
        } catch (Exception e) {
            e.printStackTrace();
            liveData.postValue("Error parsing error response");
        }
    }

}
