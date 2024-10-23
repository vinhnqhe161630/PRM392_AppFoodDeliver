package com.example.fooddelivery_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.AuthRepository.LoginRepository;

public class LoginViewModel extends ViewModel {
    private LoginRepository loginRepository;

    public LoginViewModel() {
        loginRepository = new LoginRepository();
    }

    public LiveData<String> login(String email, String password) {
        return loginRepository.login(email, password);
    }
}
