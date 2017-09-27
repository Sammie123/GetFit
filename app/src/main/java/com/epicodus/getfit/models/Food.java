package com.epicodus.getfit.models;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Parcel
public class Food {
     String RecipeName;
     String ImageUrl;
     ArrayList<String> Ingredients;
     private String pushId;

    public Food() {}

    public Food(String name, String image, ArrayList<String> ingredients) {
        this.RecipeName = name;
        this.ImageUrl = image;
        this.Ingredients = ingredients;
    }

    public String getName() {

        return RecipeName;
    }

    public String getImage() {

        return ImageUrl;
    }

    public ArrayList<String> getIngredients() {
        return Ingredients;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}