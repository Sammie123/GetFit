package com.epicodus.getfit.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.getfit.Constants;
import com.epicodus.getfit.NutritionDetailFragment;
import com.epicodus.getfit.ui.NutritionDetailActivity;
import com.epicodus.getfit.R;
import com.epicodus.getfit.models.Food;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    @Override
    public FoodListAdapter.FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food, parent, false);
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

    public class FoodViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        @Bind(R.id.foodNameTextView) TextView mFoodNameTextView;
        @Bind(R.id.foodImageView) ImageView mFoodImageView;
        private Context mContext;
        private int mOrientation;

        public FoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        private void createDetailFragment(int position) {
            NutritionDetailFragment detailFragment = NutritionDetailFragment.newInstance(mFoods, position);
            FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.foodDetailContainer, detailFragment);
            ft. commit();
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, NutritionDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_FOODS, Parcels.wrap(mFoods));
                mContext.startActivity(intent);
            }
        }

        public void bindFood(Food food) {
            Picasso.with(mContext).load(food.getImage()).into(mFoodImageView);
            mFoodNameTextView.setText(food.getName());
        }
    }
}
