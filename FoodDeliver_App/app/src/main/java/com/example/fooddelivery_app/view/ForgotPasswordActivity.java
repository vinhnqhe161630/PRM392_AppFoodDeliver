package com.example.fooddelivery_app.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.viewmodel.ForgotPassViewModel;
import com.example.fooddelivery_app.viewmodel.LoginViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {


    private ForgotPassViewModel forgotPassViewModel;
    private EditText emailEditText;
    private Button forgotbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.emailforgot);
        forgotbtn = findViewById(R.id.forgotbtn);
        forgotPassViewModel = new ViewModelProvider(this).get(ForgotPassViewModel.class);

        forgotPassViewModel.getSuccessLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {

                Toast.makeText(ForgotPasswordActivity.this, result, Toast.LENGTH_SHORT).show();

            }
        });

        forgotPassViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                // Hiển thị lỗi nếu có
                Toast.makeText(ForgotPasswordActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

        forgotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if (!email.isEmpty()) {
                    // Gọi phương thức đăng nhập từ ViewModel
                    forgotPassViewModel.forgotPass(email);
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Please input email", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}