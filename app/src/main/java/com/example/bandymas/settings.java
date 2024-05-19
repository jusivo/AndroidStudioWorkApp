package com.example.bandymas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

public class settings extends AppCompatActivity {

    private AlertDialog.Builder alert;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, home_page.class);
                startActivity(intent);

            }
        });
        alert = new AlertDialog.Builder(this);
        //clickable button configurations:
        AppCompatButton account_btnn = findViewById(R.id.account_btn);
        AppCompatButton profile = findViewById(R.id.profile_btn);
        AppCompatButton privacy = findViewById(R.id.privacy_btn);
        AppCompatButton log_out = findViewById(R.id.log_out_btn);

        account_btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, account_changing_settings.class);
                startActivity(intent);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, account_details.class);
                startActivity(intent);

            }
        });
        //veliau padaryti:

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setTitle("Do you really want to log out?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth fAuth = FirebaseAuth.getInstance();
                        fAuth.signOut();
                        Intent intent = new Intent(settings.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alert.show();


            }
        });

    }
}