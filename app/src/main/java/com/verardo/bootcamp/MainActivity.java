package com.verardo.bootcamp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public static final String NAME = "com.verardo.myapp.NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO: Remove this line of code once I learn about asynchronous operations!
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void goWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, GreetingActivity.class);
        TextView textView = findViewById(R.id.mainName);
        String name = textView.getText().toString();
        intent.putExtra(NAME, name);
        startActivity(intent);
    }
}