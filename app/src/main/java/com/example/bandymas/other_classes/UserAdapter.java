package com.example.bandymas.other_classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bandymas.ChatClasses.Users;
import com.example.bandymas.R;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private List<Users> userlist;
    private FirebaseUser firebaseUser;
    //String friendid;


    public UserAdapter(Context context, List<Users> userlist) {
        this.context = context;
        this.userlist = userlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_display, parent, false);
        return new UserAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        Users userss = userlist.get(position);
        holder.name.setText(userss.getName());
        holder.surname.setText(userss.getSurname());
        if (userss.getImageUri() ==null)
        {
            holder.imageView.setImageResource(R.drawable.no_ft);
        }
        else
        {
            Glide.with(context).load(userss.getImageUri()).into(holder.imageView);        }
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView surname;

        //TextView messagetext, seen;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.NameTextView);
            surname = itemView.findViewById(R.id.SurnameTextView);
            imageView = itemView.findViewById(R.id.user_profileView);

        }
    }
}
