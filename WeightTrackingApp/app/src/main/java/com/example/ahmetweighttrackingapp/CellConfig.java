package com.example.ahmetweighttrackingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CellConfig extends ArrayAdapter<Weight> {

    // Using this class to configure an array adapter for the list view of all the weights/dates to help populate view with data
    public CellConfig(Context context, List<Weight> weights) {
        super(context, 0, weights);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weight weight = getItem(position);
        if(convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.weight_cell, parent, false);

        TextView weightV = convertView.findViewById(R.id.weightCell);
        TextView dateV = convertView.findViewById(R.id.dateCell);

        weightV.setText(weight.getWeight());
        dateV.setText(weight.getDate().toString());

        return convertView;
    }
}
