package com.zee.projectosos.model;

import com.nguyenhoanglam.imagepicker.model.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;

public  class basicDataType {

    String title ;
    ArrayList<Image> images ;

    public basicDataType(String title, ArrayList<Image> images)
    {
        this.title = title ;
        this.images = images ;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
