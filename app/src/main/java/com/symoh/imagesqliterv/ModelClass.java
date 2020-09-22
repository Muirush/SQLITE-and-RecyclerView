package com.symoh.imagesqliterv;

import android.graphics.Bitmap;

public class ModelClass {
        private   String ImageName;
        private Bitmap image;

    public ModelClass(String imageName, Bitmap image) {
        this.ImageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        this.ImageName = imageName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
