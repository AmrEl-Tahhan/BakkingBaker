package com.example.bakkingbaker.RetrofitAPI;


import com.example.bakkingbaker.Room.Category;
import com.example.bakkingbaker.Room.Ingredient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    // 1- define Base and Feed
    String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";
    String FEED = "topher/2017/May/59121517_baking/baking.json";

    // 2 - refrofit object
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // 3- how am going to make the request
    @GET(FEED)
    Call<Category[]> categories();




    
}
