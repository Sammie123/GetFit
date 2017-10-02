package com.epicodus.getfit.ui;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.epicodus.getfit.Constants;
import com.epicodus.getfit.R;
import com.epicodus.getfit.adapters.FoodListAdapter;
import com.epicodus.getfit.models.Food;
import com.epicodus.getfit.services.YummlyService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.Call;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Response;

public class NutritionListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private FoodListAdapter mAdapter;
    public ArrayList<Food> mFoods = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchFoodReference;
    private String mRecentSearch;
    private ValueEventListener mSearchFoodReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);
    }
}



