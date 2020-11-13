package pt.ua.nextweather.network;

import java.util.HashMap;

import pt.ua.nextweather.datamodel.City;

public interface  CityResultsObserver {
    public HashMap<String, City> receiveCitiesList(HashMap<String, City> citiesCollection);
    public void onFailure(Throwable cause);
}
