package com.example.urban_crew_extended;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {


    public CustomAdapter(@NonNull Context context, ArrayList<CustomSpinnerItems> customlist) {
        super(context, 0, customlist);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_layout,parent,false);
        }

        CustomSpinnerItems items = (CustomSpinnerItems) getItem(position);
        TextView spinner_1 = convertView.findViewById(R.id.spinner_1);
        if (items != null) {
            spinner_1.setText(items.getSpinnerItemname());
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dropdown_layout,parent,false);
        }

        CustomSpinnerItems items = (CustomSpinnerItems) getItem(position);
        TextView spinner_Dropdown = convertView.findViewById(R.id.dropdown_1);
        if (items != null) {
            spinner_Dropdown.setText(items.getSpinnerItemname());
        }

        return convertView;
    }
}
