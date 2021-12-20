package com.example.newsapp.models;

import java.util.ArrayList;

public class NewsResponse {
    public ArrayList<Articles> articles;

    public NewsResponse(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    //GETTER
    public ArrayList<Articles> getArticles() {
        return articles;
    }

    //SETTER
    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
