package com.example.bandymas;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bandymas.other_classes.sql_db;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;

public class account_details extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private AppCompatButton openRegions;
    private ImageButton backBtn;
    private String Town;
    private Uri photoUser;
    private EditText description;
    
    //private sql_db db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        ImageView profile = findViewById(R.id.imageView2_profile);
        description = findViewById(R.id.descriptionAdd);
        String descriptionn = description.getText().toString();
        //inflation of menu:
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getMenuInflater().inflate(R.menu.menu_items, toolbar.getMenu());
        pickImage(profile);
        openRegions = findViewById(R.id.location_change_btn);
        getRegionNames(openRegions);
        //displayTextFromDatabase();
        backBtn = findViewById(R.id.imageButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account_details.this, home_page.class);
                startActivity(intent);
                finish();
            }
        });
        //town gavimas
        //Intent getValue = getIntent();
        //Town = getValue.getStringExtra("passTown");
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Town = preferences.getString("selectedTown", null);

        openRegions.setText(Town);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("savedTown", Town);
    }

    //for selecting town:
    private void getRegionNames(AppCompatButton btn)
    {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(account_details.this, selectingTown.class);
                startActivity(intent);
            }
        });
    }
    //method for creation of  ActivityResultLauncher
    private void pickImage(ImageView profilee)
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = o.getData();
                            //URI photo = o.getData();
                            if (data!= null)
                            {
                                Uri photo = data.getData();
                                photoUser = photo;
                                SharedPreferences preferences = getSharedPreferences("MyPrefsJob", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit()
                                        ;
                                editor.putString("selectedProfilePicture", photoUser.toString());//use imageview to change uri?
                                profilee.setImageURI(photoUser);
                            }
                        }
                    }
                }
        );
        FloatingActionButton open_gallery = findViewById(R.id.floatingActionButton_photo);
        open_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someActivityResultLauncher.launch(gallery);

            }
        });
    }
    private void changeInformation(ImageButton change, String descrption)
    {
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // descrption.s
            }
        });
    }

}