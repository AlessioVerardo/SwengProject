package com.verardo.bootcamp.weather;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.NoSuchElementException;

import javax.net.ssl.HttpsURLConnection;

public class WeatherServices {
    private static String API_KEY = "91ef81ace8be76de2831b122a76b77f0";
    private static String BASE_URL = "https://api.openweathermap.org/data/2.5/onecall?";
    public static Weather getWeatherAtLocation(Location loc) throws Exception {
        String query = BASE_URL + "lat=" + String.valueOf(loc.getLatitude()) + "&lon=" + String.valueOf(loc.getLongitude()) + "&units=metric&exclude=minutely,hourly,alerts&appid=" + API_KEY;
        URL url = new URL(query);
        System.out.println("OUI0");
        InputStream stream = null;
        HttpsURLConnection connection = null;
        System.out.println("OUI1");
        connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(3000);
        connection.setConnectTimeout(3000);
        connection.setRequestMethod("GET");
        System.out.println("OUI2");
        // Already true by default but setting just in case; needs to be true since this request
        // is carrying an input (response) body.
        connection.setDoInput(true);
        System.out.println("OUI3");
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
