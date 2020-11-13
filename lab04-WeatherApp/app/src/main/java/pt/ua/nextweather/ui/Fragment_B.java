package pt.ua.nextweather.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;

public class Fragment_B extends Fragment {
    private TextView date;
    private TextView pprob;
    private TextView tmin;
    private TextView tmax;
    private TextView wind;
    private TextView weathertype;
    private TextView windspeed;
    private String feedback = "";
    private String city;
    private int pointer =0;
    private HashMap<String,String> days = new HashMap<>();
    private ArrayList<HashMap<String,String>> forecasts = new ArrayList<>();
    IpmaWeatherClient iwc = new IpmaWeatherClient();

    public Fragment_B() {
        // Required empty public constructor
    }

    public static Fragment_B newInstance(String city) {
        Fragment_B fragment = new Fragment_B();
        Bundle args = new Bundle();

        args.putString("CityName",city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment__b, container, false);

        date = view.findViewById(R.id.date);
        pprob = view.findViewById(R.id.pprob);
        tmin = view.findViewById(R.id.tmin);
        tmax = view.findViewById(R.id.tmax);
        wind = view.findViewById(R.id.wind);
        weathertype = view.findViewById(R.id.wtype);
        windspeed = view.findViewById(R.id.windspeed);


        TextView tv = (TextView) view.findViewById(R.id.cityView);
        TextView tvcode = (TextView) view.findViewById(R.id.code);
        city = this.getArguments().getString("CityName");
        tv.setText(city);
        tvcode.setText(String.valueOf(CityListAdapter.cCityList.get(city).getGlobalIdLocal()));

        callWeatherForecastForACityStep(CityListAdapter.cCityList.get(city).getGlobalIdLocal(),0);

        Button next = (Button) view.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(pointer<4){
                callWeatherForecastForACityStep(CityListAdapter.cCityList.get(city).getGlobalIdLocal(),++pointer);
                }
            }
        });

        Button prev = (Button) view.findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(pointer>0) {
                    callWeatherForecastForACityStep(CityListAdapter.cCityList.get(city).getGlobalIdLocal(), --pointer);
                }
            }
        });

        return view;

    }

    private void callWeatherForecastForACityStep(int localId,int dateDay) {
        iwc.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                    Object[] superforecast = forecast.toArray();
                    Weather day = (Weather)superforecast[dateDay];

                    days.put("date",day.getForecastDate());
                    days.put("pprob", String.valueOf(day.getPrecipitaProb()));
                    days.put("tmin", String.valueOf(day.getTMin()));
                    days.put("tmax", String.valueOf(day.getTMax()));
                    days.put("wind",day.getPredWindDir());
                    days.put("weathertype", String.valueOf(day.getIdWeatherType()));
                    days.put("windspeed", String.valueOf(day.getClassWindSpeed()));
                    forecasts.add(days);

                date.setText(forecasts.get(dateDay).get("date"));
                pprob.setText(forecasts.get(dateDay).get("pprob"));
                tmin.setText(forecasts.get(dateDay).get("tmin"));
                tmax.setText(forecasts.get(dateDay).get("tmax"));
                wind.setText(forecasts.get(dateDay).get("wind"));
                weathertype.setText(forecasts.get(dateDay).get("weathertype"));
                windspeed.setText(forecasts.get(dateDay).get("windspeed"));
                Log.i("feed",forecasts.toString());
            }
            @Override
            public void onFailure(Throwable cause) {
                System.out.println( "Failed to get forecast for 5 days");
            }
        });


    }
}