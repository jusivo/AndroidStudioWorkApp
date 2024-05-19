package com.example.bandymas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bandymas.other_classes.sql_db;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changeEmail extends AppCompatActivity {
    private EditText emailEnter;
    private AlertDialog.Builder alertDialog;
    //private sql_db database;
    private AppCompatButton btn;
    private ImageButton back;
    private TextView textView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        mAuth = FirebaseAuth.getInstance();
        btn = findViewById(R.id.changingEmailBtn);
        back = findViewById(R.id.back_to_settings);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changeEmail.this, account_changing_settings.class);
                startActivity(intent);


            }
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        emailEnter = findViewById(R.id.newEmailEntered);
        textView = findViewById(R.id.display_email);
        String newEmailExtracted = emailEnter.getText().toString();
        alertDialog = new AlertDialog.Builder(this);
        changingEmail(newEmailExtracted, btn, user, textView, alertDialog);
    }
    private void changingEmail(String new_em, AppCompatButton button, FirebaseUser user, TextView view, AlertDialog.Builder builder)
    {
        //database = new sql_db(this);
        //String email_existing = database.getEmail();
        //final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //final Boolean[] updated = {false};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Do you want to change your email address?").setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (user!=null)
                        {
                            //database.updateEmail(email_existing, newEmailExtracted);
                            //updated[0] = true;
                            user.verifyBeforeUpdateEmail(new_em);
                            view.setText(new_em);
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

    }

}

