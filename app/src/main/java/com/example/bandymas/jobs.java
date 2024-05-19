package com.example.bandymas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.bandymas.other_classes.CustomAdapter;
import com.example.bandymas.other_classes.JobInformation;
import com.example.bandymas.other_classes.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class jobs extends AppCompatActivity {
    private String title, descriptionUser, categoryOneUser, categoryTwoUser, phoneNumberUser, price;
    private boolean advertisementsLoaded = false;
    private Uri imageUrii;
    private  ArrayList<JobInformation> userModels;
    private CustomAdapter listViewAdapter;
    private ImageButton backBtn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        if (userModels == null) {
            userModels = new ArrayList<>();
        }

        listView = findViewById(R.id.followersList);
        ImageButton vector_click = findViewById(R.id.add_aJob_btn);
        vector_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(jobs.this, job_fill_form.class);
                startActivity(intent);
            }
        });

        backBtn = findViewById(R.id.imageButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jobs.this, home_page.class);
                startActivity(intent);
            }
        });
        //other toolbar buttons:
        ImageButton messg = findViewById(R.id.message_btn);
        ImageButton searchh = findViewById(R.id.search_button);
        ImageButton notificationss = findViewById(R.id.notifications_btn);
        messg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jobs.this, messaging.class);
                startActivity(intent);
            }
        });
        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jobs.this, searchView.class);
                startActivity(intent);
            }
        });
        //ateity pataisyti!!
        notificationss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(jobs.this, notifications_.class);
                startActivity(intent);
            }
        });
        if (listViewAdapter == null) {
            listViewAdapter = new CustomAdapter(this, userModels);
            listView.setAdapter(listViewAdapter);
        }
        //job info implementation:
        if (!advertisementsLoaded)
        {
            loadJobs();
            advertisementsLoaded = true;
        }

       /* CustomAdapter listViewAdapter = new CustomAdapter(this, userModels);
        listView.setAdapter(listViewAdapter);

        */
        //listViewAdapter.notifyDataSetChanged();
        //listview on click:
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTwo = new Intent(jobs.this, jobFullView.class);
                startActivity(intentTwo);
            }
        });
    }
    private void loadJobs()
    {
        SharedPreferences preferences = getSharedPreferences("MyPrefsJob", MODE_PRIVATE);
        title = preferences.getString("selectedTitle", null);
        String imgUri = preferences.getString("selectedURL", null);
        //--------------------------------------------------------------

        if (imgUri != null)
        {
            imageUrii = Uri.parse(imgUri);
            userModels.add(new JobInformation(title, imageUrii));
        }
        else
        {
            int defaultDrawableResId = R.drawable.no_ft;
            imageUrii = Uri.parse("android.resource://" + getPackageName() + "/" + defaultDrawableResId);
            userModels.add(new JobInformation(title, imageUrii));
        }
        if (listViewAdapter != null) {
            listViewAdapter.notifyDataSetChanged();
        }
    }
    /*public static void addJobInformation(JobInformation jobInformation) {
        userModels.add(jobInformation);
    }

     */


}