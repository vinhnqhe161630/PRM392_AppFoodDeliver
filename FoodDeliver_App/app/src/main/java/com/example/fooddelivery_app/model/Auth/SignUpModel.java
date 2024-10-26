package com.example.fooddelivery_app.model.Auth;

public class SignUpModel {
    private String Name;
    private String Email;
    private String Pass;

    public SignUpModel() {
    }

    public SignUpModel(String name, String email, String pass) {
        Name = name;
        Email = email;
        Pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
