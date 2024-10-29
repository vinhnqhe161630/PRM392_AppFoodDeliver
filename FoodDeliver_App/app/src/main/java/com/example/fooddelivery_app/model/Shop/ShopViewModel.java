package com.example.fooddelivery_app.model.Shop;

import java.util.UUID;

public class ShopViewModel {

    private UUID id;
    private String name;
    private String email;
    private String pass;
    private String img; // Sử dụng String vì img có thể là URL hoặc đường dẫn
    private String phone;
    private String address;
    private double vote;
    private String role;
    private boolean status;
    private int totalOrder;

    public ShopViewModel() {
    }

    public ShopViewModel(UUID id, String name, String email, String pass, String img,
                         String phone, String address,
                         double vote, String role, boolean status, int totalOrder) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.img = img;
        this.phone = phone;
        this.address = address;
        this.vote = vote;
        this.role = role;
        this.status = status;
        this.totalOrder = totalOrder;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }
}
