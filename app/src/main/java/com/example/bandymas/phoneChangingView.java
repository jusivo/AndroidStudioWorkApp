package com.example.bandymas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.car.ui.AlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class phoneChangingView extends AppCompatActivity {
    private AlertDialog.Builder alertDialog;

    private TextView phone;
    private AppCompatButton saveButton;
    private ImageButton back;
    private TextInputEditText inputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_changing_view);

        phone = findViewById(R.id.phone_view);
        saveButton = findViewById(R.id.save_phone);
        inputEditText = findViewById(R.id.phone_input);
        savingPhone(saveButton, phone);
        alertDialog = new AlertDialog.Builder(this);
        back = findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phoneChangingView.this, account_changing_settings.class);
                startActivity(intent); // Use a unique request code
            }
        });
    }
    private void savingPhone(AppCompatButton button,  TextView textView_)
    {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        alertDialog.setTitle("Do you really want to change phone number?").setCancelable(true)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String  text_ = inputEditText.getText().toString();
                                        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();

                                        editor.putString("phoneNumber", text_);
                                        editor.apply();
                                        textView_.setText(text_);

                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                        alertDialog.show();

                //textView.setText(text);



                //updating:
                //updatingPhoneNumber();


            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updatingPhoneNumber();
    }

    private void updatingPhoneNumber()
    {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String phoneNumber = preferences.getString("phoneNumber", "");
        phone.setText(phoneNumber);
    }
    //method for checking if a number is correct
    /*private boolean isCorrect(String text)
    {
        for (int i = 0; i< text.length(); i++)
        {
            if (!Character.isDigit(text.charAt(i)))
            {
                Toast.makeText("")
                return false;

            }
        }
        return true;

    }

     */
}