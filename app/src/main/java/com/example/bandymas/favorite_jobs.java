package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bandymas.other_classes.CustomAdapter;
import com.example.bandymas.other_classes.JobInformation;

import java.util.ArrayList;

public class favorite_jobs extends AppCompatActivity {
    private ArrayList<JobInformation> userModels;
    private CustomAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_jobs);



    }
}