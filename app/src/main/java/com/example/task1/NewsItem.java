package com.example.task1;

import com.google.gson.annotations.SerializedName;

public class NewsItem {
    private String title;
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    public NewsItem(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

}
