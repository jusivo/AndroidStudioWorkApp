package com.example.bandymas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.example.bandymas.other_classes.SearchAdapter;
import com.example.bandymas.search_view_class_other.Adapter;


public class searchView extends AppCompatActivity {
    private SearchView searchView;
    SearchAdapter adapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        //importJobCategories();
        searchView = findViewById(R.id.search_bar);
        Resources resources = getResources();
        String[] stringArray = resources.getStringArray(R.array.job_select);
        List<String>lst = Arrays.asList(stringArray);
        adapter = new SearchAdapter(this, lst);
        listView = findViewById(R.id.list_view_adapter);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Intent intent = new Intent(com.example.bandymas.searchView.this, )
                    case 1:
                    case 2:

                }


            }
        });
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    private  void importJobCategories(String[]stringArray)
    {


       // List<String> data = Arrays.asList(Arrays.toString(stringArray));
        // Replace with your data
    }

}