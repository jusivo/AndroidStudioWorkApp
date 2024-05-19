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

import com.android.car.ui.AlertDialogBuilder;
import com.example.bandymas.other_classes.sql_db;
import com.example.bandymas.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class new_password_form extends AppCompatActivity {
    private AlertDialogBuilder builder;
    private FirebaseAuth mAuth;
    private EditText email_existing, password_new, passw_repeat;
    private AppCompatButton change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password_form);
        mAuth = FirebaseAuth.getInstance();
        //user changing password:
        email_existing = findViewById(R.id.email_enter);
        password_new = findViewById(R.id.new_passw);
        passw_repeat = findViewById(R.id.repeat_passw);
        String email = email_existing.getText().toString();
        String passw_n = password_new.getText().toString();
        String passw_r = passw_repeat.getText().toString();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfEmpty(passw_n, passw_r, email))
                {
                    if (checkPasswordCredentials(passw_n))
                    {
                        setPasswordNew(passw_n, email, passw_r);
                        Toast.makeText(new_password_form.this, "Password changed succesfully", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        //back
        ImageButton back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(new_password_form.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean checkIfEmpty(String passw, String passw_r, String email_)
    {
        if (passw.equals("") || passw_r.equals("") || email_.equals(""))
        {
            return true;

        }
        return false;
    }
    //method for changing password:
    private void setPasswordNew(String passwordNew, String emailAddress, String repeatPassw)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null && user.getEmail().equals(emailAddress) && passwordNew.equals(repeatPassw))
        {
            user.updatePassword(passwordNew)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Password update successful
                                Toast.makeText(new_password_form.this, "Password updated", Toast.LENGTH_SHORT).show();
                            } else {
                                // If updating password fails, handle the error
                                Toast.makeText(new_password_form.this, "Failed to update password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }



        };

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
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
                    if (!hasSpecialCharacter)
                    {
                        Toast.makeText(new_password_form.this, "The password should" +
                                " have at least one special character", Toast.LENGTH_SHORT).show();
                    }


                }
            }
            Toast.makeText(new_password_form.this, "The password should have an upper case letter", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(new_password_form.this, "The password is too short", Toast.LENGTH_SHORT).show();
        }
        return false;

    }

}