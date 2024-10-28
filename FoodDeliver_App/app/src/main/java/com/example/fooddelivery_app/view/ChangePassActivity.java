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
import com.example.fooddelivery_app.viewmodel.ChangePasswordViewModel;
import com.example.fooddelivery_app.viewmodel.ForgotPassViewModel;

public class ChangePassActivity extends AppCompatActivity {


 private EditText oldpassEditText,newPassEditText,confirmpassEditText;
 private Button changepassbtn;
    private ChangePasswordViewModel changePasswordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_pass);

        oldpassEditText=findViewById(R.id.oldpass);
        newPassEditText=findViewById(R.id.newpass);
        confirmpassEditText=findViewById(R.id.confirmpass);
        changepassbtn=findViewById(R.id.changepassbtn);

        changePasswordViewModel = new  ViewModelProvider(this).get(ChangePasswordViewModel.class);
        changepassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpass = oldpassEditText.getText().toString();
                String newPass = newPassEditText.getText().toString();
                String confirmpass = confirmpassEditText.getText().toString();

                if (oldpass.isEmpty() || newPass.isEmpty() || confirmpass.isEmpty()) {
                    Toast.makeText(ChangePassActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                if(!newPass.equals(confirmpass)) {
                    Toast.makeText(ChangePassActivity.this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show();

                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                    String authToken = sharedPreferences.getString("auth_token", null);
                    if (authToken == null) {
                        Toast.makeText(ChangePassActivity.this, "Authentication token not found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    changePasswordViewModel.changePassword(authToken,oldpass,newPass);
                }

            }
        });

        changePasswordViewModel.getSuccessLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {

                Toast.makeText(ChangePassActivity.this, result, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChangePassActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        changePasswordViewModel.getErrorLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                // Hiển thị lỗi nếu có
                Toast.makeText(ChangePassActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });

    }
}