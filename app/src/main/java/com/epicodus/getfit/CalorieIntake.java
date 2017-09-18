package com.epicodus.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        mUserAge.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 0) {
                    mUserAge.setError("Invalid Response");
                }

            }
        });

//        final String age1 = mUserAge.getText().toString();
//        final String weight1 = mUserWeight.getText().toString();
//
//            if(mUserAge.getText().toString().length() == 0 ) {
//                mUserAge.setError("Invalid Response");
//                mUserAge.requestFocus();
//            }

//            if(mUserWeight.getText().toString().length() == 0 ) {
//                mUserWeight.setError("Invalid Response");
//                mUserWeight.requestFocus();
//            }

        mUserInputInformation.setText("Based on your age and your current weight, your daily caloric intake is " +  calculateCalorie(age, weight) + " calories");

    }
}