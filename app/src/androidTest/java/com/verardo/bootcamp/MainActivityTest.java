package com.verardo.bootcamp;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> testRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void intentIsCreatedAndContainsAString(){
        Intents.init();
        Espresso.onView((withId(R.id.mainName))).perform(ViewActions.clearText());
        Espresso.onView(withId(R.id.mainName)).perform(ViewActions.typeText("RogerMike"));
        Espresso.onView(withId(R.id.mainGoButton)).perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasExtra(MainActivity.NAME,"RogerMike"));


        Intents.release();
    }
}
