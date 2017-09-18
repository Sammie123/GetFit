package com.epicodus.getfit.models;

public class Food {
    private String mRecipeName;
    private String mImage;

    public Food(String name, String image) {
        this.mRecipeName = name;
        this.mImage = image;
    }


    public String getName() {

        return mRecipeName;
    }

    public String getImage() {
        return mImage;
    }

}