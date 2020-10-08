package com.verardo.bootcamp;
import com.verardo.bootcamp.weather.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class WeatherActivity extends AppCompatActivity {
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    }

    public static Context getContext(){
        return context;
    }

    public void switchListener(View v){
        Switch sw = findViewById(R.id.switchLocation);
        if(sw.isChecked()){
            findViewById(R.id.addressSearch).setVisibility(View.INVISIBLE);
        }else{
            findViewById(R.id.addressSearch).setVisibility(View.VISIBLE);
        }
    }

    private void setCorrectParameter(View v){
        GridLayout.LayoutParams lp2 = new GridLayout.LayoutParams();
        lp2.width = 0;
        lp2.height=100;
        lp2.setGravity(Gravity.CENTER);
        v.setLayoutParams(lp2);
        ((GridLayout.LayoutParams) v.getLayoutParams()).setGravity(Gravity.FILL_HORIZONTAL);
        ((GridLayout.LayoutParams) v.getLayoutParams()).columnSpec =
                GridLayout.spec(GridLayout.UNDEFINED, 1f);
    }

    public void getWeatherForecast(View v) throws Exception{
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {

        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {

        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // Check Permissions Now
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2);
        } else {

        }

        Location loc;
        if(((Switch)findViewById(R.id.switchLocation)).isChecked())
            loc = LocationServices.getCurrentLocation();
        else
            loc = GeocodingServices.convertAddressToLocation(((TextView)findViewById(R.id.addressSearch)).getText().toString());
        System.out.println(loc.getLongitude());
        Weather we = WeatherServices.getWeatherAtLocation(loc);

        GridLayout gl = findViewById(R.id.gridLayout);
        gl.removeAllViews();

        TextView dayE = new TextView(this);
        dayE.setText("Day");
        setCorrectParameter(dayE);
        gl.addView(dayE);

        TextView tempE = new TextView(this);
        tempE.setText("Temp.");
        setCorrectParameter(tempE);
        gl.addView(tempE);

        TextView feelLikeE = new TextView(this);
        feelLikeE.setText("Feel like");
        setCorrectParameter(feelLikeE);
        gl.addView(feelLikeE);

        TextView humiE = new TextView(this);
        humiE.setText("Humidity");
        setCorrectParameter(humiE);
        gl.addView(humiE);

        TextView winSE = new TextView(this);
        winSE.setText("Wind speed");
        setCorrectParameter(winSE);
        gl.addView(winSE);

        TextView empty = new TextView(this);
        empty.setText("  ");
        setCorrectParameter(empty);
        gl.addView(empty);



        for(int j =0; j<7; ++j){
            Weather.DailyWeather cur = we.getForecastWeather()[j];

            TextView day = new TextView(this);
            day.setText(cur.getDay());
            setCorrectParameter(day);
            gl.addView(day);

            TextView tvTemp = new TextView(this);
            tvTemp.setText(String.valueOf(cur.getCurrentTemperature()));
            setCorrectParameter(tvTemp);
            gl.addView(tvTemp);

            TextView tvTempFeel = new TextView(this);
            tvTempFeel.setText(String.valueOf(cur.getFeelLikeTemperature()));
            setCorrectParameter(tvTempFeel);
            gl.addView(tvTempFeel);

            TextView tvHumidity = new TextView(this);
            tvHumidity.setText(String.valueOf(cur.getHumidity()));
            setCorrectParameter(tvHumidity);
            gl.addView(tvHumidity);

            TextView tvWindSpeed = new TextView(this);
            tvWindSpeed.setText(String.valueOf(cur.getWindSpeed()));
            setCorrectParameter(tvWindSpeed);
            gl.addView(tvWindSpeed);

            ImageView img = new ImageView(this);
            URL url = new URL("https://openweathermap.org/img/wn/"+cur.getIconId()+".png");
            Bitmap img2 = BitmapFactory.decodeStream(((HttpsURLConnection)url.openConnection()).getInputStream());
            img.setImageBitmap(img2);
            gl.addView(img);
        }
    }
}