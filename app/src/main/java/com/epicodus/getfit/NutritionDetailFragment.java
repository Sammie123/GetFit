package com.epicodus.getfit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.epicodus.getfit.models.Food;
import org.parceler.Parcels;
import org.w3c.dom.Text;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NutritionDetailFragment extends Fragment implements  View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.foodImageView) ImageView mImageLabel;
    @Bind(R.id.foodNameTextView) TextView mFoodNameTextView;
    @Bind(R.id.saveFoodButton) Button mSaveFoodButton;
    @Bind(R.id.ingredientsTextView) TextView mIngredientsTextView;
    private ArrayList<Food> mFoods;
    private int mPosition;
    private Food mFood;

    public static NutritionDetailFragment newInstance(ArrayList<Food> foods, Integer position) {
        NutritionDetailFragment nutritionDetailFragment = new NutritionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.EXTRA_KEY_FOODS, Parcels.wrap(foods));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);
        nutritionDetailFragment.setArguments(args);
        return nutritionDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFoods = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_FOODS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mFood = mFoods.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition_detail, container, false);
        ButterKnife.bind(this,view);

        mImageLabel.setOnClickListener(this);
        mSaveFoodButton.setOnClickListener(this);

        Picasso.with(view.getContext()).load(mFood.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);
        mFoodNameTextView.setText(mFood.getName());
        mIngredientsTextView.setText(android.text.TextUtils.join(", ", mFood.getIngredients()));
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mImageLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.myfitnesspal.com"));
            startActivity(webIntent);
        }

        if (v == mSaveFoodButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference foodRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_FOOD)
                    .child(uid);

            DatabaseReference pushRef = foodRef.push();
            String pushId = pushRef.getKey();
            mFood.setPushId(pushId);
            pushRef.setValue(mFood);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
