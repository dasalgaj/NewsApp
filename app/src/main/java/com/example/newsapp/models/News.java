package com.example.newsapp.models;

import java.util.ArrayList;

public class News {
    public String uuid;
    public String title;

    public News(String uuid, String title) {
        this.uuid = uuid;
        this.title = title;
    }

    //GETTER
    public String getTitle() {
        return title;
    }

    public String getUuid() {
        return uuid;
    }

    //SETTER
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
