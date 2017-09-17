package com.epicodus.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Callback;
import okhttp3.Call;
import java.io.IOException;
import okhttp3.Response;

public class Nutrition extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = Nutrition.class.getSimpleName();
    @Bind(R.id.searchFood) EditText mSearchFood;
    @Bind(R.id.search_button) Button mSearchButton;
    @Bind(R.id.listView) ListView mListView;

    private String[] nutritionList = new String[] { "apple", "banana", "orange" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);

        ArrayAdapter arrayAdapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, nutritionList);
        mListView.setAdapter(arrayAdapter);

        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String food = mSearchFood.getText().toString();
        getFood(food);
    }

    private void getFood(String food) {
        final YummlyService yummlyService = new YummlyService();
        yummlyService.findFood(food, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}



