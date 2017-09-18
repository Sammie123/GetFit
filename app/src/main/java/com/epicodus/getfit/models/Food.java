package com.epicodus.getfit.models;

import org.parceler.Parcel;
import java.util.ArrayList;

@Parcel
public class Food {
     String mRecipeName;
     String mImage;

    public Food() {}

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