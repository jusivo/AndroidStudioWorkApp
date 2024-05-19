package com.example.bandymas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;

public class home_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //ListView list_menu = findViewById(R.id.list_item_menu);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.scroll_items, android.R.layout.simple_list_item_1);
        //list_menu.setAdapter(adapter);
        //inflates the menu
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getMenuInflater().inflate(R.menu.menu_items, toolbar.getMenu());
        //==>icon clickable button for creating new job listing => takes to creation form<==:
        ImageButton vector_click = findViewById(R.id.add_aJob_btn);
        vector_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(home_page.this, job_fill_form.class);
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
                Intent intent = new Intent(home_page.this, messaging.class);
                startActivity(intent);

            }
        });

        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, searchView.class);
                startActivity(intent);

            }
        });
        //ateity pataisyti!!

        notificationss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_page.this, notifications_.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //prideda meniu bar i toolbar
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.menu_jobs)
        {
            intent = new Intent(this, jobs.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_account)
        {
            intent = new Intent(this, account_details.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menu_settings)
        {
            intent = new Intent(this, settings.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.menu_rules) {
            intent = new Intent(this, rules_of_app.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //jeigu
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id)
    {
        String selectedItem = (String) parent.getItemAtPosition(pos);
        if (selectedItem.equals("My account"))
        {

        }
        else if (selectedItem.equals("Jobs"))
        {

        }
        else if(selectedItem.equals("Rules"))
        {

        }
        else if(selectedItem.equals("Settings"))
        {

        }


    }


}