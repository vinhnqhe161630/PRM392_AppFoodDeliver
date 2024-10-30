package com.example.fooddelivery_app.viewmodel.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.AuthRepository;

public class ChangePasswordViewModel extends ViewModel {
    private AuthRepository authRepository;
    private MutableLiveData<String> successLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public ChangePasswordViewModel() {
        authRepository = new AuthRepository();
    }
    public LiveData<String> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void changePassword(String token,String oldpass, String newpass) {
        authRepository.ChangePassword(token,oldpass,newpass).observeForever(result -> {
            if (result.startsWith("Fail") || result.startsWith("Error")) {
                errorLiveData.setValue(result);
            } else {
                successLiveData.setValue(result);
            }
        });
    }
}
