package com.example.souq.source.network;

import com.example.souq.models.response.categoryResponse.CategoryResponse;
import com.example.souq.models.response.loginResponse.LoginResponse;
import com.example.souq.models.response.registerResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apis {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password,
            @Field("phone") String phone);


    @GET("categories")
    Call<CategoryResponse> category();
}
