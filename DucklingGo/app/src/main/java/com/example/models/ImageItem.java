package com.example.models;

import android.widget.ImageView;
import android.widget.TextView;

public class ImageItem {
    private String image_url;
    private String name;

    public ImageItem(String imageUrl, String name) {
        this.image_url = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return image_url;
    }

    public String getName() {
        return name;
    }
}
