package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bandymas.google_maps_ac.location_displaying;
import com.example.bandymas.other_classes.TimePicker;

public class confirm_job_LC extends AppCompatActivity {
    private AppCompatButton mapDisplayer;
    private AppCompatButton getTime;
    private ImageButton confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_job_lc);
        mapDisplayer = findViewById(R.id.map_display);
        getTime = findViewById(R.id.time_display);
        displayTime(getTime);
        showMap(mapDisplayer);
    }
    private void showMap(AppCompatButton btn1)
    {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(confirm_job_LC.this, location_displaying.class);
                startActivity(intent);
            }
        });
    }
    private void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
    private  void displayTime(AppCompatButton btn2)
    {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
    }


}