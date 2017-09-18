package com.epicodus.getfit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.bottom_navigation) BottomNavigationView mBottomNavigationView;
    @Bind(R.id.titleGetFit) TextView mTitleGetFit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");
        mTitleGetFit.setTypeface(font);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Intent intent = new Intent(MainActivity.this, CalorieIntake.class);
                        startActivity(intent);
                        break;
                    case R.id.action_add_2:
                        Intent intent2 = new Intent(MainActivity.this, NutritionListActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_add_3:
                        Intent intent3 = new Intent(MainActivity.this, ExerciseProgram.class);
                        startActivity(intent3);
                        break;

                }
                return false;
            }
        });
    }
}



