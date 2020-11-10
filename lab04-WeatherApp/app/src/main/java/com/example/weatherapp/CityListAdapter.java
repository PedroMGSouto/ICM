package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

public class CityListAdapter extends RecyclerView.Adapter<CityViewHolder>{
    private final HashMap<String,Integer> cCityList;
    private LayoutInflater cInflater;

    public CityListAdapter(Context context, HashMap<String,Integer> cityList) {
        cInflater = LayoutInflater.from(context);
        this.cCityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cItemView = cInflater.inflate(R.layout.frame_textview, parent, false);

        return new CityViewHolder(cItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        String mCurrent = cCityList.keySet().toArray()[position].toString();
        holder.view.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return cCityList.keySet().size();
    }
}