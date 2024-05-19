package com.example.bandymas.search_view_class_other;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<String> items;

    public Adapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {

        return items.size();
    }

    @Override
    public Object getItem(int position) {

        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
