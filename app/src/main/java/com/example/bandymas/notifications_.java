package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class notifications_ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageButton vector_click = findViewById(R.id.add_aJob_btn);
        vector_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(notifications_.this, job_fill_form.class);
                startActivity(intent);
            }
        });
        //other toolbar buttons:
        ImageButton messg = findViewById(R.id.message_btn);
        ImageButton searchh = findViewById(R.id.search_button);
       // ImageButton notificationss = findViewById(R.id.notifications_btn);

        messg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notifications_.this, messaging.class);
                startActivity(intent);
            }
        });

        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(notifications_.this, searchView.class);
                startActivity(intent);

            }
        });

        //back
        ImageButton back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(notifications_.this, home_page.class);
                startActivity(intent);
            }
        });

    }
}