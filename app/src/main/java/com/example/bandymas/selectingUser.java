package com.example.bandymas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class selectingUser extends AppCompatActivity {
    private DatabaseReference reference;
    //private FirebaseAuth mAuth;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_user);

        ImageButton back = findViewById(R.id.back_to_mess_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectingUser.this, messaging.class);
                startActivity(intent);
            }
        });
        autoCompleteTextView = findViewById(R.id.selection_of_user);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        getUsersReferance(adapter);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
    private void getUsersReferance(ArrayAdapter<String> arrayAdapter)
    {
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        ArrayList<String> emails = new ArrayList<String>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String email = dataSnapshot.child("email").getValue(String.class);
                    if (email!=null)
                    {
                        emails.add(email);
                    }

                }
                arrayAdapter.addAll(emails);
                arrayAdapter.notifyDataSetChanged();


            }
            //

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}