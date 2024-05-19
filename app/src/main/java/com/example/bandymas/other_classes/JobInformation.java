package com.example.bandymas.other_classes;

import android.net.Uri;

public class JobInformation {
    private String title;
    private Uri imgUri;

    public JobInformation(String title, Uri imgUri) {
        this.title = title;
        this.imgUri = imgUri;
    }
    public String getTitle()
    {
        return title;
    }
    public Uri getImgUri()
    {
        return imgUri;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public void setImgUri(Uri photo)
    {
        this.imgUri = photo;

    }
}
