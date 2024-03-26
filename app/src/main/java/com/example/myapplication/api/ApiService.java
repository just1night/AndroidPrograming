package com.example.myapplication.api;

import com.example.myapplication.novel.Novel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> RegisterUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("gettopnovel.php")
    Call<ArrayList<Novel>> Getlist5(@Field("") String dummy);

}
