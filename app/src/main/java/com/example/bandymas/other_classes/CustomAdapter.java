package com.example.bandymas.other_classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.util.TableInfo;

import com.example.bandymas.R;
import com.example.bandymas.jobFullView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<JobInformation> {
    Context context;
    private ArrayList<JobInformation> userModels;
    public CustomAdapter(Context context, ArrayList<JobInformation> userModels) {
        super(context, 0, userModels);
        this.context = context;
        this.userModels = userModels;
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public JobInformation getItem(int position) {
        return userModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.activity_adapter_job_view, parent, false);
        ImageView image = convertView.findViewById(R.id.listImg);
        image.setImageURI((userModels.get(position).getImgUri()));
        TextView name = convertView.findViewById(R.id.nameSurname);
        name.setText(userModels.get(position).getTitle());

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTwo = new Intent(context, jobFullView.class);
                context.startActivity(intentTwo);
            }
        });


        return convertView;
    }

}
