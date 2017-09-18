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
        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_APP_ID);
        urlBuilder.addQueryParameter("_app_key", Constants.YUMMLY_APP_KEY);
        urlBuilder.addQueryParameter(Constants.YUMMLY_SEARCH, food);

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
                    JSONArray matchesJSON = yummlyJSON.getJSONArray("matches");
                    for (int i = 0; i < matchesJSON.length(); i++) {
                        JSONObject foodJSON = matchesJSON.getJSONObject(i);
                        String name = foodJSON.getString("recipeName");
                        String image = foodJSON.getString("imageUrlsBySize");

                        Food food = new Food(name, image);
                        foods.add(food);
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

