package com.example.myapplication.api;

import com.example.myapplication.classobject.Account;
import com.example.myapplication.novel.Novel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //đăng nhập
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    //đăng kí
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> RegisterUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    //lấy top 5 truyên đầu tiên
    @FormUrlEncoded
    @POST("gettopnovel.php")
    Call<ArrayList<Novel>> Getlist5(@Field("") String dummy);

    @FormUrlEncoded
    @POST("loaduser.php")
    Call<Account> Getuser(@Field("username") String username);

    //đánh giá truyện
    @FormUrlEncoded
    @POST("Voting.php")
    Call<VoteResponse> VoteResp(
            @Field("idacc") int idacc,
            @Field("idnovel") int idnovel,
            @Field("rating")  float rating
    );

    //Truyện được đánh giá
    @FormUrlEncoded
    @POST("takeAVGrate.php")
    Call<RatingResponse> RateofNovel(
        @Field("idnovel") int idnovel
    );


}
