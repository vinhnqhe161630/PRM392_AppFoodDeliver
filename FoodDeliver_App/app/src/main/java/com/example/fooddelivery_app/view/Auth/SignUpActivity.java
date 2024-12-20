package com.example.fooddelivery_app.view.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddelivery_app.R;
import com.example.fooddelivery_app.viewmodel.Auth.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel singUpViewModel;
    private EditText emailEditText, passwordEditText,usernameEditText;
    private Button signUpButton,logInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        emailEditText = findViewById(R.id.emailSignUp);
        passwordEditText = findViewById(R.id.passwordSignUp);
        usernameEditText = findViewById(R.id.usernameSignUp);
        signUpButton = findViewById(R.id.signUpButton);
        logInButton = findViewById(R.id.lgbtn);
        // Khởi tạo ViewModel
        singUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = usernameEditText.getText().toString();
                // Gọi phương thức login trong ViewModel
                singUpViewModel.SignUp(name,email, password);
            }
        });

        singUpViewModel.getSuccessLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();
                // Chuyển hướng đến Activity
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        singUpViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                // Hiển thị lỗi nếu có
                Toast.makeText(SignUpActivity.this, "SignUP  "+error, Toast.LENGTH_SHORT).show();
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Chuyển hướng đến Activity chính (MainActivity)
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Kết thúc Activity đăng nhập
            }

        });
    }
}