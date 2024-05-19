package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class selectingTown extends AppCompatActivity {
    private ListView listView;
    private String town;
    private ImageButton back;
    private String[]array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_town);
        listView = findViewById(R.id.list_towns);
        array = getResources().getStringArray(R.array.town_select);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_town_list_view, R.id.textView7, array);
        listView.setAdapter(adapter);
        back = findViewById(R.id.backBtn);
        goBack(back);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(selectingTown.this, account_details.class);
                intent.putExtra("passTown", selectedItem);
                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("selectedTown", selectedItem);
                editor.apply();


                startActivity(intent);
            }
        });

    }
    //on click method:




    private void goBack(ImageButton goBack)
    {
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectingTown.this, account_details.class);
                startActivity(intent);

            }
        });
    }

}