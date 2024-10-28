package com.example.fooddelivery_app.repository.AuthRepository;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fooddelivery_app.model.Auth.ChangePasswordModel;
import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Auth.SignUpModel;
import com.example.fooddelivery_app.retrofit.RetrofitUtility;
import com.example.fooddelivery_app.retrofit.apis.AuthApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private AuthApiService apiService;

    public AuthRepository() {
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
                    handleSuccessResponse(response, liveData);
                } else {
                   handleErrorResponse(response, liveData);

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


    public LiveData<String> SignUp(String name, String email, String password) {
        MutableLiveData<String> liveData = new MutableLiveData<>();

        SignUpModel signUpModel = new SignUpModel(name,email,password);
        Call<ResponseBody> call = apiService.signUp(signUpModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.postValue("Sign Up Successfull");
                } else {
                    handleErrorResponse(response, liveData);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                liveData.postValue("API error: " + t.getMessage());
            }
        });
        return liveData;
    }


public LiveData<String> ChangePassword(String token,String oldPassword, String newPassword) {
        MutableLiveData<String> liveData = new MutableLiveData<>();



    ChangePasswordModel changePasswordModel = new ChangePasswordModel(token, oldPassword,newPassword);
    Call<ResponseBody> call = apiService.changePass(changePasswordModel);

    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                liveData.postValue("Change successful");
            } else {
                handleErrorResponse(response, liveData);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            liveData.postValue("API error: " + t.getMessage());
        }
    });

    return liveData;
}

public LiveData<String> ForgotPassword(String email) {
    MutableLiveData<String> liveData = new MutableLiveData<>();
    Call<ResponseBody> call = apiService.forgotPass(email);
    call.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful() && response.body() != null) {
                liveData.postValue("Email sent");
            } else {
                handleErrorResponse(response, liveData);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            liveData.postValue("Error: " + t.getMessage());
        }
    });

    return liveData;
}
    private void handleSuccessResponse(Response<ResponseBody> response, MutableLiveData<String> liveData) {
        try {
            String responseBody = response.body().string(); // Gọi .string() để lấy nội dung

            JSONObject jsonObject = new JSONObject(responseBody); // Phân tích cú pháp JSON
            String token = jsonObject.getString("token"); // Lấy token
            liveData.postValue(token); // Cập nhật token vào liveData
        } catch (JSONException e) {
            e.printStackTrace();
            liveData.postValue("Error parsing response"+ e.getMessage());
        }  catch (IOException e) {
        e.printStackTrace();
        liveData.postValue("Error reading response body: " + e.getMessage());
    }
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



