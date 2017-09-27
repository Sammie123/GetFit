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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NutritionDetailFragment extends Fragment implements  View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.foodImageView) ImageView mImageLabel;
    @Bind(R.id.foodNameTextView) TextView mFoodNameTextView;
    @Bind(R.id.saveFoodButton) Button mSaveFoodButton;

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

        mImageLabel.setOnClickListener(this);
        mSaveFoodButton.setOnClickListener(this);

        Picasso.with(view.getContext()).load(mFood.getImage())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);
        mFoodNameTextView.setText(mFood.getName());
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
