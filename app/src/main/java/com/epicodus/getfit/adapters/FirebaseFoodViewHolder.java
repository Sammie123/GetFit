package com.epicodus.getfit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.getfit.Constants;
import com.epicodus.getfit.ui.NutritionDetailActivity;
import com.epicodus.getfit.R;
import com.epicodus.getfit.models.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseFoodViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseFoodViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindFood(Food food) {
        ImageView foodImageView = (ImageView) mView.findViewById(R.id.foodImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.foodNameTextView);

        Picasso.with(mContext)
                .load(food.getImage())
                .resize(MAX_HEIGHT, MAX_WIDTH)
                .centerCrop()
                .into(foodImageView);

        nameTextView.setText(food.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Food> foods = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FOOD);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    foods.add(snapshot.getValue(Food.class));
                }

                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, NutritionDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("foods", Parcels.wrap(foods));

                mContext.startActivity(intent);
            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }
}
