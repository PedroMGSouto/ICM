package com.example.weatherapp;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView view;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.city);
    }

    public TextView getView(){
        return view;
    }

    @Override
    public void onClick(View v) {
        
    }
}