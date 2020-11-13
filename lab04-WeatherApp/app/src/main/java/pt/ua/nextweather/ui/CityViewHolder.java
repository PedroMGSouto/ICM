package pt.ua.nextweather.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import pt.ua.nextweather.R;

public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView view;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.city);
        view.setOnClickListener(this);
    }

    public TextView getView(){
        return view;
    }

    @Override
    public void onClick(View v) {
        TextView city = (TextView) v;

        Context context = v.getContext();

        Intent intent = new Intent(context, Activity_B.class);
        intent.putExtra("City",city.getText().toString());
        context.startActivity(intent);

    }
}