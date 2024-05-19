package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bandymas.other_classes.sql_db;
import com.example.bandymas.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity
{
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, confirm_job_LC.class);
                startActivity(intent);

            }
        }
        )
        ;
    }
    public void redirectToLoginActivity(View view)
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void redirectToRegistrationForm(View view)
    {
        Intent intent = new Intent(this, registration_form.class);
        startActivity(intent);
    }

}