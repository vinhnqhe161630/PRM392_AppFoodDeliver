package com.example.fooddelivery_app.viewmodel.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.AuthRepository.AuthRepository;

public class ForgotPassViewModel  extends ViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<String> successLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public ForgotPassViewModel() {
        authRepository = new AuthRepository();
    }
    public LiveData<String> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void forgotPass(String email) {
        authRepository.ForgotPassword(email).observeForever(result -> {
            if (result.startsWith("Fail") || result.startsWith("Error")) {
                errorLiveData.setValue(result);
            } else {
                successLiveData.setValue(result);
            }
        });
    }
}
