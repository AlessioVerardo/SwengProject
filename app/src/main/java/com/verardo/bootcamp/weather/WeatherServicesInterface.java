package com.verardo.bootcamp.weather;

public interface WeatherServicesInterface{
    public Weather getWeatherAtLocation(Location loc) throws Exception;
}
