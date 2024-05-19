package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class adapter_job_view extends AppCompatActivity {
    private ImageButton heart_favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_job_view);
        heart_favorite = findViewById(R.id.clickable_heart);
        heart_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(ada.this, jobs.class);
                heart_favorite.setImageResource(R.drawable.baseline_favorite_24);
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
            }
        });
    }
}