package com.example.fooddelivery_app.retrofit.apis;

import com.example.fooddelivery_app.model.Auth.LoginModel;
import com.example.fooddelivery_app.model.Food.Food;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.Comment.AddCommentDto;
import com.example.fooddelivery_app.model.Comment.CommentDto;
import com.example.fooddelivery_app.model.Comment.PurchaseCheckDto;
import com.example.fooddelivery_app.model.Food.FoodDetailDto;
import com.example.fooddelivery_app.model.Food.FoodDto;
import com.example.fooddelivery_app.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;



public interface FoodApiService {
    @GET("Food")
    Call<List<FoodDto>> getFoods();

    @GET("Foods/account/{accountId}")
    Call<List<FoodDto>> getFoodsByAccountId(@Path("accountId") String accountId);
    @GET("foods/{foodId}/details")
    Call<FoodDto> getFoodDetail(@Path("foodId") String foodId);

    @GET("comments/food/{foodId}") // Define the endpoint for fetching comments by foodId
    Call<List<CommentDto>> getCommentsByFoodId(@Path("foodId") String foodId);

    @POST("comments/add")
    Call<AddCommentDto> addComment(@Body AddCommentDto commentDto);

    @GET("comments/check-purchase/{userId}/{foodId}")
    Call<PurchaseCheckDto> checkPurchase(@Path("userId") String userId, @Path("foodId") String foodId);

    @DELETE("comments/{id}")
    Call<Void> deleteComment(@Path("id") String commentId);

}

