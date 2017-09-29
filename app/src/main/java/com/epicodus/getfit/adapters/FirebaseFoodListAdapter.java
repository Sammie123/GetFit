package com.epicodus.getfit.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.getfit.NutritionDetailFragment;
import com.epicodus.getfit.models.Food;
import com.epicodus.getfit.ui.NutritionDetailActivity;
import com.epicodus.getfit.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.epicodus.getfit.adapters.FirebaseFoodViewHolder;
import com.epicodus.getfit.util.ItemTouchHelperAdapter;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseFoodListAdapter extends FirebaseRecyclerAdapter <Food, FirebaseFoodViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Food> mFoods = new ArrayList<>();


    public FirebaseFoodListAdapter(Class<Food> modelClass, int modelLayout, Class<FirebaseFoodViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mFoods.add(dataSnapshot.getValue(Food.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseFoodViewHolder viewHolder, Food model, int position) {
        viewHolder.bindFood(model);
        viewHolder.mFoodImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NutritionDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("foods", Parcels.wrap(mFoods));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mFoods, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position){
        mFoods.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexinFirebase() {
        for (Food food: mFoods) {
            int index = mFoods.indexOf(food);
            DatabaseReference ref = getRef(index);
            food.setIndex(Integer.toString(index));
            ref.setValue(food);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexinFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
