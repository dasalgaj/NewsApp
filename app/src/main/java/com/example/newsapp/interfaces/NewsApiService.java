package com.example.newsapp.interfaces;

import com.example.newsapp.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApiService {
    @GET("/v2/top-headlines?country=us&apiKey=c7d7c79fdcc04088a499db2f1803d606")
    Call<NewsResponse> getArticles();
}
