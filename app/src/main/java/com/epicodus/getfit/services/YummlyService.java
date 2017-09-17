package com.epicodus.getfit.services;

import android.util.Log;

import com.epicodus.getfit.Constants;
import com.epicodus.getfit.models.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class YummlyService {
    public static void findFood(String food, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YUMMLY_SEARCH, food);
//        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_APP_ID);
        urlBuilder.addQueryParameter("api_key", Constants.YUMMLY_APP_KEY);

        String url = urlBuilder.build().toString();
        Log.v(TAG, url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
        public ArrayList<Food> processResults(Response response) {
            ArrayList<Food> foods = new ArrayList<>();

            try {
                String jsonData = response.body().string();
                if (response.isSuccessful()) {
                    JSONObject yummlyJSON = new JSONObject(jsonData);
                    JSONArray itemJSON = yummlyJSON.getJSONArray("item");
                    for (int i = 0; i < itemJSON.length(); i++) {
                        JSONObject mealJSON = itemJSON.getJSONObject(i);
                        String name = mealJSON.getString("name");

                        Food food1 = new Food(name);
                        foods.add(food1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return foods;
        }

    }

