package com.epicodus.getfit;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class CalorieIntakeInstrumentedTest {

    @Rule
    public ActivityTestRule<CalorieIntake> activityTestRule =
            new ActivityTestRule<>(CalorieIntake.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.userAge)).perform(typeText(String.valueOf("15")))
                .check(matches(withText("15")));
    }

//    @Test
//    public void ageAndWeightCalculated() {
//        onView(withId(R.id.userAge)).perform(typeText(String.valueOf("15")));
//        onView(withId(R.id.userWeight)).perform(typeText(String.valueOf("10")));
//        onView(withId(R.id.findDailyCalorieButton)).perform(click());
//        onView(withId(R.id.userInputInformation)).check(matches (withText("Based on your age and your current weight, your daily caloric intake is 300")));
//    }
}





