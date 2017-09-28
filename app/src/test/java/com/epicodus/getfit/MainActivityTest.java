package com.epicodus.getfit;

import android.os.Build;

import com.epicodus.getfit.ui.MainActivity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

//    @Test
//    public void validateTextViewContent() {
//        TextView titleGetFit = (TextView) activity.findViewById(R.id.titleGetFit);
//        assertTrue("Welcome to GetFIT".equals(titleGetFit.getText().toString()));
//    }

//    @Test
//    public void secondActivityStarted() {
//        activity.findViewById(R.id.bottom_navigation).performClick();
//        Intent expectedIntent = new Intent(activity, CalorieIntake.class);
//        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
//        Intent actualIntent;
//        actualIntent = shadowActivity.getNextStartedActivity();
//        assertTrue(actualIntent.filterEquals(expectedIntent));
//    }
}
