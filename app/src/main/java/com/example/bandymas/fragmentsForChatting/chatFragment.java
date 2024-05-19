package com.example.bandymas.fragmentsForChatting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bandymas.ChatClasses.Users;
import com.example.bandymas.R;
import com.example.bandymas.other_classes.UserAdapter;

import java.util.List;

public class chatFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}