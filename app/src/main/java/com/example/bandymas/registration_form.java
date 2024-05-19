package com.example.bandymas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.bandymas.other_classes.sql_db;
import com.example.bandymas.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kotlin.text.Regex;

public class registration_form extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText  email, password, surname, name;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        mAuth = FirebaseAuth.getInstance();
        //su duombaze:
        //db = new sql_db(this);
        AppCompatButton sign_up = findViewById(R.id.button4);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                email = findViewById(R.id.reg_email);
                password = findViewById(R.id.reg_passw);
                name = findViewById(R.id.registrationName);
                surname = findViewById(R.id.reg_surname);
                String user = email.getText().toString();
                String surnamee = surname.getText().toString();
                String namee = name.getText().toString();
                String passw = password.getText().toString();
                //kodas:
                if (user.equals("")|| passw.equals("") || namee.equals("")|| surnamee.equals(""))
                {
                    Toast.makeText(registration_form.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                        //boolean check_if_exists = db.checkEmail(user);
                        //if (check_if_exists == false)
                        //{
                            if (checkPasswordCredentials(passw) == true)
                            {
                                creatingEmailPassword(user, passw, mAuth, namee, surnamee);
                                creatingEmailPassword(user, passw, mAuth, namee, surnamee);
                                Toast.makeText(registration_form.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), home_page.class);
                                startActivity(intent);
                                //saveAdditionalUserInfo(username);
                            }

                            //handle registration failure:
                       // }
                        else
                        {
                            Toast.makeText(registration_form.this, "The user with given email already exists", Toast.LENGTH_SHORT).show();
                        }
                        //Toast.makeText(registration_form.this, "Password does not match", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //back
        ImageButton back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(registration_form.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //checking if password length >= 8, first letter is upper case,
    // and if password contains at least one or
    // more special characters:
    private boolean checkPasswordCredentials(String passw)
    {
        //Regex regex = new Regex();
        String crediant = "!@#$%^&*()_+=~`:;<>";
        if (passw.length() >= 8)
        {
            boolean hasSpecialCharacter = false;
            boolean upper_let = false;
            for (int i = 0; i< passw.length(); i++)
            {
                for (int j = 0; j< crediant.length(); j++)
                {
                    if (Character.isUpperCase(passw.charAt(0)) )
                    {
                        upper_let = true;
                    }
                    if (crediant.contains(String.valueOf(passw.charAt(i)))) {
                        hasSpecialCharacter = true;
                    }

                    if (upper_let && hasSpecialCharacter) {
                        return true;
                    }
                    if (upper_let)
                    {
                        Toast.makeText(registration_form.this, "The password should" +
                                " have at least one special character", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            Toast.makeText(registration_form.this, "The password should have an upper case letter", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(registration_form.this, "The password is too short", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
    //method for registering:
    private void creatingEmailPassword(String emaill, String passw, FirebaseAuth mAuth, String namee, String surnamee)
    {
        mAuth.createUserWithEmailAndPassword(emaill, passw)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(registration_form.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), home_page.class);
                            startActivity(intent);
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser!=null)
                            {
                                saveAdditionalInformation(currentUser.getUid(), namee, surnamee);
                            }
                            // Sign in success, update UI with the signed-in user's information
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(registration_form.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //method for storing the users name and surname in a firebase:
    private void saveAdditionalInformation(String userId,  String namee, String surnamee)
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userId).child("name").setValue(namee);
        databaseReference.child(userId).child("surname").setValue(surnamee);

    }

}