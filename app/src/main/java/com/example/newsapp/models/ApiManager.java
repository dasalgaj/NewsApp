package com.example.newsapp.models;

import com.example.newsapp.interfaces.NewsApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    static ApiManager instance;
    private NewsApiService service;

    private ApiManager(){
        Retrofit.Builder builder = new Retrofit.Builder();

        Retrofit retrofit = builder
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NewsApiService.class);
    }

    public static ApiManager getInstance(){
        if (instance == null){
            instance = new ApiManager();
        }
        return instance;
    }

    public NewsApiService service(){
        return service;
    }
}
