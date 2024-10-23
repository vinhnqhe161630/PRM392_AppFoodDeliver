package com.example.fooddelivery_app.repository.AuthRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.ApiService;
import com.example.fooddelivery_app.retrofit.apis.AuthApiService;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private AuthApiService apiService;

    public LoginRepository() {
        apiService = RetrofitUtility.getClient().create(AuthApiService.class);

    }
    public LiveData<String> login(String email, String password) {
        // Khởi tạo MutableLiveData để lưu trữ token hoặc thông báo lỗi
        MutableLiveData<String> liveData = new MutableLiveData<>();

        // Tạo yêu cầu đăng nhập
        LoginModel loginRequest = new LoginModel(email, password);
        Call<ResponseBody> call = apiService.loginUser(loginRequest);
        // Thực hiện cuộc gọi API bất đồng bộ
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        // Chuyển đổi body thành String
                        String responseBody = response.body().string();

                        // Sử dụng JSONObject để lấy token từ chuỗi JSON
                        JSONObject jsonObject = new JSONObject(responseBody);
                        String token = jsonObject.getString("token");

                        // Cập nhật token vào liveData
                        liveData.postValue(token);
                    } catch (Exception e) {
                        e.printStackTrace();
                        liveData.postValue("Error parsing response");
                    }
                } else {
                    liveData.postValue("Login failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveData.postValue("API error: " + t.getMessage());
            }
        });

        // Trả về liveData
        return liveData;
    }
    }


