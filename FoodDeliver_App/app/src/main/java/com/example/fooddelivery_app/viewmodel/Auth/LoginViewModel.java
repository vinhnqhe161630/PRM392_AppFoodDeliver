package com.example.fooddelivery_app.viewmodel.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.AuthRepository;

public class LoginViewModel extends ViewModel {
    private AuthRepository loginRepository;
    private MutableLiveData<String> successLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LoginViewModel() {
        loginRepository = new AuthRepository();
    }

    public LiveData<String> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void login(String email, String password) {
       loginRepository.login(email, password).observeForever(result -> {
            if (result.startsWith("Fail") || result.startsWith("Error")) {
                errorLiveData.setValue(result);
            } else {
                successLiveData.setValue(result);
            }
        });
    }
}
