package com.epicodus.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.epicodus.getfit.adapters.FoodListAdapter;
import com.epicodus.getfit.models.Food;
import com.epicodus.getfit.services.YummlyService;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.Call;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class NutritionListActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = NutritionListActivity.class.getSimpleName();
    @Bind(R.id.searchFood) EditText mSearchFood;
    @Bind(R.id.search_button) Button mSearchButton;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;

    public ArrayList<Food> mFoods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String food = mSearchFood.getText().toString();
        getFoods(food);
    }

    private void getFoods(String food) {
        final YummlyService yummlyService = new YummlyService();
        yummlyService.findFood(food, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mFoods = yummlyService.processResults(response);
                NutritionListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new FoodListAdapter(getApplicationContext(), mFoods);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NutritionListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                });
            }
        });

    }

}



