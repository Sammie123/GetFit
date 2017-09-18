package com.epicodus.getfit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.getfit.models.Food;

import org.parceler.Parcels;
import com.squareup.picasso.Picasso;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NutritionDetailFragment extends Fragment {
    @Bind(R.id.foodImageView) ImageView mImageLabel;
    @Bind(R.id.foodNameTextView) TextView mFoodNameTextView;

    private Food mFood;


    public static NutritionDetailFragment newInstance(Food food) {
        NutritionDetailFragment nutritionDetailFragment = new NutritionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("food", Parcels.wrap(food));
        nutritionDetailFragment.setArguments(args);
        return nutritionDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFood = Parcels.unwrap(getArguments().getParcelable("food"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition_detail, container, false);
        ButterKnife.bind(this,view);


        Picasso.with(view.getContext()).load(mFood.getImage()).into(mImageLabel);
        mFoodNameTextView.setText(mFood.getName());
        return view;
    }

}
