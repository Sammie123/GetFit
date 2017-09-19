package com.epicodus.getfit.models;

import org.parceler.Parcel;

@Parcel
public class Food {
     String Name;
     String Image;

    public Food() {}

    public Food(String name, String image) {
        this.Name = name;
        this.Image = image;
    }

    public String getName() {

        return Name;
    }

    public String getImage() {
        return Image;
    }

}