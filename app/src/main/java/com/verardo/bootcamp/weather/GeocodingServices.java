package com.verardo.bootcamp.weather;

import android.location.Address;
import android.location.Geocoder;
import com.verardo.bootcamp.WeatherActivity;

import java.util.List;
import java.util.NoSuchElementException;

public class GeocodingServices {
    public static Location convertAddressToLocation(String address) throws Exception{
        Geocoder geo = new Geocoder(WeatherActivity.getContext());
        List<Address> addresses = geo.getFromLocationName(address,1);
        if(addresses == null || addresses.isEmpty())
            throw new NoSuchElementException("We couldn't find any adresses corresponding to the given one");
        return new Location(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
    }
}
