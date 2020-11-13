package pt.ua.nextweather.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;

public class Fragment_A extends Fragment {
    private RecyclerView recyclerView;

    public IpmaWeatherClient client = new IpmaWeatherClient();

    private HashMap<String, City> cCityList = new HashMap<>();

    public Fragment_A() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__a, container, false);

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        getAllCities();


        return view;
    }


    public void getAllCities(){
        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public HashMap<String, City> receiveCitiesList(HashMap<String, City> citiesCollection) {
                recyclerView.setAdapter(new CityListAdapter(getContext(),citiesCollection));
                return citiesCollection;
            }

            @Override
            public void onFailure(Throwable cause) {
                Toast.makeText(getActivity(), "Failed to get City List. Are you connected to the internet?", Toast.LENGTH_LONG).show();
            }
        });
    }

}