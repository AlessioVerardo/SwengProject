package com.verardo.bootcamp;

import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GreetingActivityTest {

    @Test
   public void nameIsWrittenOnTextView(){
       Intent intent = new Intent(ApplicationProvider.getApplicationContext(),GreetingActivity.class);
       String name = "RogerMike";
       intent.putExtra(MainActivity.NAME,name);
       ActivityScenario a = ActivityScenario.launch(intent);

       Espresso.onView(withId(R.id.greetingMessage)).check(ViewAssertions.matches(ViewMatchers.withText("Hi there, welcome back "+name)));
       a.close();
   }
}