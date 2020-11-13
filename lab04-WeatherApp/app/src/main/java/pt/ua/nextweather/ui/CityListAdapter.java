package pt.ua.nextweather.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.TreeSet;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;

public class CityListAdapter extends RecyclerView.Adapter<CityViewHolder>{
    public static HashMap<String, City> cCityList;
    private LayoutInflater cInflater;

    public CityListAdapter(Context context, HashMap<String, City> cityList) {
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
        TreeSet<String> keys = new TreeSet<>(cCityList.keySet());
        String mCurrent = keys.toArray()[position].toString();
        holder.view.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return cCityList.keySet().size();
    }
}