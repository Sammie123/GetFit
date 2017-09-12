package com.epicodus.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.findDailyCalorieButton) Button mFindDailyCalorieButton;
    @Bind(R.id.userAge) EditText mUserAge;
    @Bind(R.id.userWeight) EditText mUserWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindDailyCalorieButton.setOnClickListener(this);

    }

        @Override
            public void onClick(View view) {
                String age = mUserAge.getText().toString();
                String weight = mUserWeight.getText().toString();
                Intent intent = new Intent(MainActivity.this, CalorieIntake.class);
                intent.putExtra("age", age);
                intent.putExtra("weight", weight);
                startActivity(intent);
            }
        }

