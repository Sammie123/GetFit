package com.epicodus.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExerciseProgram extends AppCompatActivity {
    private String[] exercises = new String[] {"Bench", "Squats", "Deadlift"};
    private String[] muscles = new String[] {"Chest, Triceps", "Quads, Abs, Hamstrings", "Back, Hamstrings"};
    @Bind(R.id.listView) ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_program);
        ButterKnife.bind(this);

        MyGetFitArrayAdapter adapter = new MyGetFitArrayAdapter(this, android.R.layout.simple_list_item_1, exercises, muscles);
        mListView.setAdapter(adapter);


    }
}
