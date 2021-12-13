package com.example.newsapp.interfaces;

import com.example.newsapp.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApiService {
    @GET("/filterWebContent?token=2841a6e8-792a-4ca0-a83d-5f754e35526f&q=site_type:news%20country:HR")
    Call<NewsResponse> getNews();
}
