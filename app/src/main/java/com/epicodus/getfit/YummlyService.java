package com.epicodus.getfit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by LMNH on 9/14/17.
 */

//public class YummlyService {
//    public static void findFood(String food, Callback callback) {
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YUMMLY_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter(Constants.YUMMLY_SEARCH, food);
//        urlBuilder.addQueryParameter("_app_id", Constants.YUMMLY_APP_ID);
//        urlBuilder.addQueryParameter("_app_key", Constants.YUMMLY_APP_KEY);
//
//        String url = urlBuilder.build().toString();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//
//    }
//}
