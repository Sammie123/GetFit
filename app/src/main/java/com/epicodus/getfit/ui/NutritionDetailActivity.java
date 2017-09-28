package com.epicodus.getfit.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.getfit.R;
import com.epicodus.getfit.adapters.NutritionPagerAdapter;
import com.epicodus.getfit.models.Food;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NutritionDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private NutritionPagerAdapter adapterViewPager;
    ArrayList<Food> mFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_detail);
        ButterKnife.bind(this);

        mFoods = Parcels.unwrap(getIntent().getParcelableExtra("foods"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new NutritionPagerAdapter(getSupportFragmentManager(), mFoods);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
