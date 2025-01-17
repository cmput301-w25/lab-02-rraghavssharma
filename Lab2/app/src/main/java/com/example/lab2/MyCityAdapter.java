package com.example.lab2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.core.content.ContextCompat;

import java.util.List;


public class MyCityAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mCityList;
    private int selectedPosition = -1; // which row is selected (-1 means none)

    public MyCityAdapter(Context context, int resource, List<String> cities) {
        super(context, resource, cities);
        this.mContext = context;
        this.mCityList = cities;
    }

    /**
     *  Update which row is currently selected
     */
    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged(); // refresh list to highlight
    }

    /**
     *  Get which row is currently selected (for deletion, etc.)
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == selectedPosition) {
            view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
        } else {
            view.setBackgroundColor(ContextCompat.getColor(mContext, android.R.color.white));
        }

        return view;
    }
}