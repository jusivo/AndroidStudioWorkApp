package com.example.bandymas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.bandymas.other_classes.sql_db;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class account_changing_settings extends AppCompatActivity {
    AlertDialog.Builder alert;
    private FirebaseAuth mAuth;
    private AppCompatButton change_passw;
    private AppCompatButton  changeEmail, deleteAcc, changePhoneNum;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_changing_settings);
        mAuth = FirebaseAuth.getInstance();
        deletionAccount();
        goToChangePassw();
        setChangeEmail();

        backBtn = findViewById(R.id.imageButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_changing_settings.this, home_page.class);
                startActivity(intent);
            }
        });

        changePhoneNum = findViewById(R.id.phone_change);
        changePhoneNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_changing_settings.this, phoneChangingView.class);
                startActivity(intent);
            }
        });
    }
    private void deletionAccount()
    {
        alert = new AlertDialog.Builder(this);
        //db = new sql_db(this);
        FirebaseUser user = mAuth.getCurrentUser();
        //String email = db.getEmail();
        deleteAcc = findViewById(R.id.remove_account_btn);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setTitle("Do you really want to delete your account?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (user != null)
                                {
                                    user.delete();
                                    Intent intent = new Intent(account_changing_settings.this, MainActivity.class);
                                    startActivity(intent);
                                }
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
    private void setChangeEmail()
    {
        changeEmail = findViewById(R.id.email_change_btn);
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_changing_settings.this, changeEmail.class);
                startActivity(intent);
            }
        });

    }
    //changing password using firebase:
    private void goToChangePassw()
    {
        //FirebaseUser user = mAuth.getCurrentUser();
        change_passw = findViewById(R.id.paasw_change_btn);
        change_passw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_changing_settings.this, new_password_form.class);
                startActivity(intent);
            }
        });




    }


}