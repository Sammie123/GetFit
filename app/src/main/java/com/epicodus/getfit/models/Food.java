package com.epicodus.getfit.models;

import org.parceler.Parcel;

@Parcel
public class Food {
     String RecipeName;
     String ImageUrl;

    public Food() {}

    public Food(String name, String image) {
        this.RecipeName = name;
        this.ImageUrl = image;
    }

    public String getName() {

        return RecipeName;
    }

    public String getImage() {
        return ImageUrl;
    }
}