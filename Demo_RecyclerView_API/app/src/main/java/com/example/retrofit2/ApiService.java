package com.example.retrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("User") // Change this to your real endpoint
    Call<List<User>> getUsers();
    @GET("posts/{id}") // Change this to your real endpoint
    Call<User> getUser(@Path("id")int id);
}
