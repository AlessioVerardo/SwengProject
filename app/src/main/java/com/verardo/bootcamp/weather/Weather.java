package com.verardo.bootcamp.weather;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Weather {
    private DailyWeather current;
    private DailyWeather[] forecastWeather;

    public DailyWeather getCurrent() {
        return current;
    }

    public DailyWeather[] getForecastWeather() {
        return forecastWeather;
    }

    public Weather(String jsonEncoding) throws Exception{
        JSONObject json = (JSONObject)new JSONTokener(jsonEncoding).nextValue();
        JSONObject current = json.getJSONObject("current");
        this.current =  new DailyWeather(getTemperature(current,true),getFeelLikeTemperature(current,true),getHumidity(current),getWindSpeed(current),getIconId(current),getDay(current));
        forecastWeather = new DailyWeather[7];
        JSONArray sevenDays = json.getJSONArray("daily");
        for(int i =0; i<7;++i){
            JSONObject curDay = sevenDays.getJSONObject(i);
            forecastWeather[i] = new DailyWeather(getTemperature(curDay,false),getFeelLikeTemperature(curDay,false),getHumidity(curDay),getWindSpeed(curDay),getIconId(curDay),getDay(curDay));
        }
    }

    public class DailyWeather{
        private double currentTemperature;
        private double feelLikeTemperature;
        private double humidity;
        private double windSpeed;
        private String iconId;
        private String day;

        public DailyWeather(double currentTemperature, double feelLikeTemperature, double humidity, double windSpeed, String iconId, String day){
            this.currentTemperature = currentTemperature;
            this.feelLikeTemperature = feelLikeTemperature;
            this.humidity = humidity;
            this.windSpeed = windSpeed;
            this.iconId = iconId;
            this.day = day;
        }

        public double getCurrentTemperature() {
            return currentTemperature;
        }

        public double getFeelLikeTemperature() {
            return feelLikeTemperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getWindSpeed() {
            return windSpeed;
        }

        public String getIconId() {
            return iconId;
        }

        public String getDay() {
            return day;
        }
    }

    private double getTemperature(JSONObject json, boolean current) throws Exception{
        if(current){
            return json.getDouble("temp");
        }else{
            return json.getJSONObject("temp").getDouble("day");
        }
    }
    private double getFeelLikeTemperature(JSONObject json, boolean current) throws Exception{
        if(current){
            return json.getDouble("feels_like");
        }else{
            return json.getJSONObject("feels_like").getDouble("day");
        }
    }
    private double getHumidity(JSONObject json) throws Exception{
        return json.getDouble("humidity");
    }
    private double getWindSpeed(JSONObject json) throws Exception{
        return json.getDouble("wind_speed");
    }
    private String getIconId(JSONObject json) throws Exception{
        return ((JSONObject)json.getJSONArray("weather").get(0)).getString("icon");
    }

    private String getDay(JSONObject json) throws  Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        return sdf.format(new Date(Long.parseLong(json.getString("dt"))*1000));
    }
}