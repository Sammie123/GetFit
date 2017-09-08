package com.epicodus.getfit;
import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by LMNH on 9/8/17.
 */

public class MyGetFitArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mExercises;
    private String[] mMuscles;

    public MyGetFitArrayAdapter(Context mContext, int resource, String[] mExercises, String[] mMuscles) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mExercises = mExercises;
        this.mMuscles = mMuscles;

    }

    @Override
    public Object getItem(int position) {
        String exercises = mExercises[position];
        String muscles = mMuscles[position];
        return String.format("%s \nTarget Muscle: %s", exercises, muscles);
    }

    @Override
    public int getCount() {
        return mExercises.length;
    }
}