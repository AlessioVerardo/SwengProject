package com.verardo.bootcamp.weather;


import android.content.Context;

import com.verardo.bootcamp.R;
import com.verardo.bootcamp.WeatherActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.NoSuchElementException;

import javax.net.ssl.HttpsURLConnection;

public class WeatherServices implements WeatherServicesInterface {
    private Context context;
    public WeatherServices(Context context){
        this.context = context;
    }
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/onecall?";

    public Weather getWeatherAtLocation(Location loc) throws Exception {
        String API_KEY = context.getString(R.string.openweather_api_key);
        String query = BASE_URL + "lat=" + String.valueOf(loc.getLatitude()) + "&lon=" + String.valueOf(loc.getLongitude()) + "&units=metric&exclude=minutely,hourly,alerts&appid=" + API_KEY;
        URL url = new URL(query);
        InputStream stream;
        HttpsURLConnection connection;
        connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(3000);
        connection.setConnectTimeout(3000);
        connection.setRequestMethod("GET");

        // Already true by default but setting just in case; needs to be true since this request
        // is carrying an input (response) body.
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();
        // Do something with responseCode
        if(responseCode == connection.HTTP_OK){
            stream = connection.getInputStream();
            // Do something with the stream
            String response = readInputStream(stream);
            stream.close();
            connection.disconnect();

            return new Weather(response);
        }else{
            connection.disconnect();
            throw new NoSuchElementException("The HTTP request wasn't accepted !");
        }
    }

    private static String readInputStream(InputStream is) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        return sb.toString();
    }
}
