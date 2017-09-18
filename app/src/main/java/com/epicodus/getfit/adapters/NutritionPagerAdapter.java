package com.epicodus.getfit.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.getfit.NutritionDetailFragment;
import com.epicodus.getfit.models.Food;

import java.util.ArrayList;


public class NutritionPagerAdapter extends FragmentPagerAdapter{
    private ArrayList<Food> mFoods;

    public NutritionPagerAdapter(FragmentManager fm, ArrayList<Food> foods) {
        super(fm);
        mFoods = foods;
    }

    @Override
    public Fragment getItem(int position) {
        return NutritionDetailFragment.newInstance(mFoods.get(position));
    }

    @Override
    public int getCount() {
        return mFoods.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFoods.get(position).getName();
    }
}
