package com.epicodus.getfit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.getfit.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalorieIntake extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = CalorieIntake.class.getSimpleName();
    @Bind(R.id.userInputInformation) TextView mUserInputInformation;
    @Bind(R.id.findDailyCalorieButton) Button mFindDailyCalorieButton;
    @Bind(R.id.userAge) EditText mUserAge;
    @Bind(R.id.userWeight) EditText mUserWeight;

    private static int calculateCalorie(String age, String weight) {
        int userAge = Integer.parseInt(age);
        int userWeight = Integer.parseInt(weight);
        int total = userAge * userWeight * 2;
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
        String age = mUserAge.getText().toString();
        String weight = mUserWeight.getText().toString();
        if(age.equals("") || weight.equals("")) {
            Toast.makeText(getApplicationContext(), "One or more fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            mUserInputInformation.setText("Based on your age and your current weight, your daily caloric intake is " +  calculateCalorie(age, weight) + " calories");
        }
    }
}