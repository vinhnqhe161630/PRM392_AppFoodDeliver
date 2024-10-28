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
import com.example.fooddelivery_app.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.rgbtn);

        // Khởi tạo ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // Đăng ký Observer cho LiveData
        loginViewModel.getSuccessLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                // Xử lý khi đăng nhập thành công
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
               editor.putString("auth_token", result);
                editor.apply();

                Toast.makeText(LoginActivity.this, "Login Successfull ", Toast.LENGTH_SHORT).show();
                // Chuyển hướng đến Activity chính (MainActivity)
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Kết thúc Activity đăng nhập
            }
        });

        loginViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                // Hiển thị lỗi nếu có
                Toast.makeText(LoginActivity.this, "Login "+error, Toast.LENGTH_SHORT).show();
            }
        });
        // Thiết lập OnClickListener cho nút đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if (!email.isEmpty() && !password.isEmpty()) {
                    // Gọi phương thức đăng nhập từ ViewModel
                    loginViewModel.login(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Please input email and password", Toast.LENGTH_SHORT).show();
                }

            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Chuyển hướng đến Activity chính (MainActivity)
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish(); // Kết thúc Activity đăng nhập
            }

        });
    }
}

