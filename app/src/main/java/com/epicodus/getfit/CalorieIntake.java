package com.epicodus.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CalorieIntake extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = CalorieIntake.class.getSimpleName();
    @Bind(R.id.userInputInformation) TextView mUserInputInformation;
    @Bind(R.id.findDailyCalorieButton) Button mFindDailyCalorieButton;
    @Bind(R.id.userAge) EditText mUserAge;
    @Bind(R.id.userWeight) EditText mUserWeight;

    private static int calculateCalorie(int age, int weight) {
        int total = age * weight * 2;
        return total;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_intake);
        ButterKnife.bind(this);

        mFindDailyCalorieButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Integer age = Integer.parseInt(mUserAge.getText().toString());
        Integer weight = Integer.parseInt(mUserWeight.getText().toString());

        mUserInputInformation.setText("Based on your age and your current weight, your daily caloric intake is " +  calculateCalorie(age, weight) + " calories");

    }
}