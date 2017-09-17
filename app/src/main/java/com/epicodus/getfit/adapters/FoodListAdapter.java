package com.epicodus.getfit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.getfit.R;
import com.epicodus.getfit.models.Food;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {
    private ArrayList<Food> mFoods = new ArrayList<>();
    private Context mContext;

    public FoodListAdapter(Context context, ArrayList<Food> foods) {
        mContext = context;
        mFoods = foods;
    }
d
    @Override
    public FoodListAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_detail, parent, false);
        FoodViewHolder viewHolder = new FoodViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FoodListAdapter.FoodViewHolder holder, int position) {
        holder.bindFood(mFoods.get(position));
    }

    @Override
    public int getItemCount() {
        return mFoods.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.nameTextView) TextView mNameTextView;

        private Context mContext;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindFood(Food food) {
            mNameTextView.setText(food.getName());
        }
    }
}
