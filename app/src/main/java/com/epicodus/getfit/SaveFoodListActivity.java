package com.epicodus.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.getfit.adapters.FirebaseFoodViewHolder;
import com.epicodus.getfit.models.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SaveFoodListActivity extends AppCompatActivity {
    private DatabaseReference mFoodReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);

        mFoodReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FOOD);
        setUpFirebaseAdapter();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mFoodReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FOOD)
                .child(uid);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Food, FirebaseFoodViewHolder>
                (Food.class, R.layout.list_food, FirebaseFoodViewHolder.class, mFoodReference) {

            @Override
            protected void populateViewHolder(FirebaseFoodViewHolder viewHolder, Food model, int position) {
                viewHolder.bindFood(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}



