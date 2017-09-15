package com.epicodus.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import okhttp3.Callback;
import okhttp3.Call;
import java.io.IOException;
import okhttp3.Response;

public class Nutrition extends AppCompatActivity {
    public static final String TAG = Nutrition.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

//        getFood(food);

    }
}

//    private void getFood(String food) {
//        final YummlyService yummlyService = new YummlyService();
//        yummlyService.findFood(food, new Callback() {
//
//            @Override
//            public void OnFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                try {
//                    String jsonData = response.body().string();
//                    Log.v(TAG, jsonData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//}

