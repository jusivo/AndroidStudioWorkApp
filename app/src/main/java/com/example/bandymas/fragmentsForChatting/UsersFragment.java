package com.example.bandymas.fragmentsForChatting;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bandymas.ChatClasses.Users;
import com.example.bandymas.R;
import com.example.bandymas.other_classes.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private List<Users> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView =view.findViewById(R.id.userdisplayRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usersList = new ArrayList<>();
        readFireBaseUsers();
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        readFireBaseUsers();
    }

    private void readFireBaseUsers(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren())
                {
                    Users users = snapshot1.getValue(Users.class);
                    if (!users.getId().equals(firebaseUser.getUid()))
                    {
                        usersList.add(users);

                    }

                }
                adapter = new UserAdapter(getContext(), usersList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}