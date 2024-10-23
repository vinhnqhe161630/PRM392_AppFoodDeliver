package com.example.fooddelivery_app.model.Auth;

public class LoginModel {
    public String Email;
    public String Pass;

    public LoginModel(String email, String pass) {
        Email = email;
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
