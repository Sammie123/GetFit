package com.epicodus.getfit.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.getfit.Constants;
import com.epicodus.getfit.R;
import com.epicodus.getfit.adapters.FirebaseFoodListAdapter;
import com.epicodus.getfit.adapters.FirebaseFoodViewHolder;
import com.epicodus.getfit.models.Food;
import com.epicodus.getfit.util.OnStartDragListener;
import com.epicodus.getfit.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SaveFoodListActivity extends AppCompatActivity implements OnStartDragListener{
    private DatabaseReference mFoodReference;
    private FirebaseFoodListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);

        mFoodReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_FOOD);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mFoodReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_FOOD)
                .child(uid);

        mFirebaseAdapter = new FirebaseFoodListAdapter(Food.class, R.layout.food_list_item_drag, FirebaseFoodViewHolder.class, mFoodReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}



