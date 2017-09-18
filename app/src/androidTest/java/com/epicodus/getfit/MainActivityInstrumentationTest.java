package com.epicodus.getfit;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

}
