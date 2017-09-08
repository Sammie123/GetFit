package com.epicodus.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalorieIntake extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.userInputInformation) TextView mUserInputInformation;
    @Bind(R.id.findExerciseList) Button mFindExerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_intake);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String age = intent.getStringExtra("age");
        String weight = intent.getStringExtra("weight");
        mUserInputInformation.setText("You enter " + age + " years and " + weight + " lbs. You're daily calorie intake based on your age and weight is 2000 calories");

        mFindExerciseList.setOnClickListener(this);

    }
            @Override
            public void onClick(View view) {
                Intent nagivateToExerciseList = new Intent(CalorieIntake.this, ExerciseProgram.class);
                startActivity(nagivateToExerciseList);
            }
        }