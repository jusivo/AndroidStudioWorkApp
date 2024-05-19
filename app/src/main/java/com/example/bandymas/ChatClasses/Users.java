package com.example.bandymas.ChatClasses;

import android.net.Uri;

public class Users
{
    private String name, surname, id;
    private Uri imageUri;

    public Users(String email, String surnamee, String id, Uri imageUri) {
        this.name = email;
        this.surname = surnamee;
        this.id = id;
        this.imageUri = imageUri;
    }

    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public String getSurname()
    {
        return surname;
    }
    public Uri getImageUri()
    {
        return imageUri;
    }

    public void setImageURL(Uri imageURL) {
        this.imageUri = imageURL;
    }
    public void setSurname(String imageURL) {
        this.surname = imageURL;
    }
    public void setId(String userId) {
        this.id = userId;
    }
    public void setName(String imageURL){this.name = imageURL;}



}

