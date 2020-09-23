package com.example.myapplication.Common;

import com.example.myapplication.Model.baseAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {


    @GET("/photos")
    Call<List<baseAPI>> getAllPosts();



}
