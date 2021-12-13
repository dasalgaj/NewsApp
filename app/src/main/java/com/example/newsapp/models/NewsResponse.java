package com.example.newsapp.models;

import java.util.ArrayList;

public class NewsResponse {
    public ArrayList<News> news;

    public NewsResponse(ArrayList<News> news) {
        this.news = news;
    }

    //GETTER
    public ArrayList<News> getNews() {
        return news;
    }

    //SETTER
    public void setNews(ArrayList<News> news) {
        this.news = news;
    }
}
