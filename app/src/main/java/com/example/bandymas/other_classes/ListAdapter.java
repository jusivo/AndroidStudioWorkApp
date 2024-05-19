package com.example.bandymas.other_classes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bandymas.R;

import java.util.ArrayList;

public class ListAdapter  {


    /*public ListAdapter(Context context, ArrayList<JobInformation> informations)
    {
        super(context, R.layout.job_list, informations);

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        JobInformation information = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job_list, parent, false);
        }
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textView =convertView.findViewById(R.id.textView6);
        if (information != null) {
            Uri photoUri = information.getPhotoUri();
            // Check if photoUri is not null before setting it to the ImageView
            if (photoUri != null) {
                imageView.setImageURI(photoUri);
            } else {
                // If photoUri is null, you might want to set a placeholder or default image
                imageView.setImageResource(R.drawable.no_ft); // Replace with your placeholder image resource
            }
            textView.setText(information.getTitle());
        }
        return convertView;
    }

     */
}
