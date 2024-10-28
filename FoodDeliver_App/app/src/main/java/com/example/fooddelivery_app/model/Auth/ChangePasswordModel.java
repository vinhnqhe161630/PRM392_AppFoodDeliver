package com.example.fooddelivery_app.model.Auth;

public class ChangePasswordModel {
    private String Token;
    private String OldPassword;
    private  String NewPassword;

    public ChangePasswordModel(String token, String oldPassword, String newPassword) {
        Token = token;
        OldPassword = oldPassword;
        NewPassword = newPassword;
    }

    public ChangePasswordModel() {
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}
