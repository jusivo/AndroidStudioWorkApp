package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class jobFullView extends AppCompatActivity {

    private String title, descriptionUser, categoryOneUser, categoryTwoUser, phoneNumberUser, price, townn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_full_view);
        TextView titlee =  findViewById(R.id.titleView);
        TextView descriptionn =  findViewById(R.id.descriptionView);
        TextView jobspec = findViewById(R.id.jobSpecificationView);
        TextView prices = findViewById(R.id.priceView);
        TextView phoneNum = findViewById(R.id.numberView);
        TextView town = findViewById(R.id.townView);
        TextView surname = findViewById(R.id.surnameView);
        ImageView imageView = findViewById(R.id.job_view_ft);

        //get Information of jobs:
        /*Intent getValue = getIntent();
        title = getValue.getStringExtra("passTitle");
         */
        SharedPreferences preferences = getSharedPreferences("MyPrefsJob", MODE_PRIVATE);
        SharedPreferences preferences_2 = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        townn = preferences_2.getString("selectedTown", null);
       // phoneNumberUser = preferences_2.getString("")
        title = preferences.getString("selectedTitle", null);
        String imgUri = preferences.getString("selectedURL", null);
        descriptionUser = preferences.getString("selectedDescription", null);
        categoryOneUser = preferences.getString("selectedCatOne", null);
        categoryTwoUser = preferences.getString("selectedCatTwo", null);
        price = preferences.getString("selectedPrice", null);
        town.setText(townn);
        titlee.setText(title);
        descriptionn.setText(descriptionUser);
        jobspec.setText(categoryOneUser);
        prices.setText(price);
        imageView.setImageURI(Uri.parse(imgUri));

    }
}