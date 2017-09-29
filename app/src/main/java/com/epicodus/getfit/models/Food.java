package com.epicodus.getfit.models;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Parcel
public class Food {
     String RecipeName;
     String ImageUrl;
     List<String> RecipeIngredients = new ArrayList<>();
     String pushId;
     String index;

    public Food() {}

    public Food(String name, String image, ArrayList<String> ingredients) {
        this.RecipeName = name;
        this.ImageUrl = image;
        this.RecipeIngredients = ingredients;
        this.index = "not_specified";
    }

    public String getName() {

        return RecipeName;
    }

    public String getImage() {

        return ImageUrl;
    }

    public List<String> getIngredients() {

        return RecipeIngredients;
    }

    public String getPushId() {

        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}