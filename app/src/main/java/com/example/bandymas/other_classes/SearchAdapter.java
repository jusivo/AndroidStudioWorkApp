package com.example.bandymas.other_classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bandymas.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<String> {
    private List<String> arrayList;
    Context context;

    public SearchAdapter(Context context, List<String> data)
    {
        super(context, 0, data);
        this.arrayList = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public String getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String information = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_view_elements, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.textView7);
        textView.setText(information);

        return convertView;
    }

}
