package com.verardo.bootcamp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        //get the name in the intent and display it in the activity
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.NAME);
        TextView tv = findViewById(R.id.greetingMessage);
        tv.setText("Hi there, welcome back " + name);
    }
}