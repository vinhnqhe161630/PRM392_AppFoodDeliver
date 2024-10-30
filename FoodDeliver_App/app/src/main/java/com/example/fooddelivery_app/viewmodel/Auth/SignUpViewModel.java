package com.example.fooddelivery_app.viewmodel.Auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddelivery_app.repository.AuthRepository;

public class SignUpViewModel extends ViewModel {
    private AuthRepository authRepository;
    private MutableLiveData<String> successLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public LiveData<String> getSuccessLiveData() {
        return successLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }
    public SignUpViewModel() {
        this.authRepository = new AuthRepository();
    }
    public void SignUp(String name,String email, String password){
         authRepository.SignUp(name,email,password ).observeForever(result -> {
             if (result.startsWith("Fail") || result.startsWith("Error")) {
                 errorLiveData.setValue(result);
             } else {
                 successLiveData.setValue(result);
             }
         });
    }
}
