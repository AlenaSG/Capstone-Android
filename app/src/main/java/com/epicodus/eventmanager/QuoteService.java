package com.epicodus.eventmanager;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

//public class QuoteService {
//    private static final String TAG = "QuoteService";
//    public Quote mQuote;
//    public static void findQuote(Callback callback) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//
//        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json").newBuilder();
//        //urlBuilder.addQueryParameter("json", format);
//        //String url = urlBuilder.build().toString();
//        String url = urlBuilder.toString();
//
//        Request request= new Request.Builder()
//                .url(url)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//
//    }
//
//    public Object processResults(Response response) {
//        //Quote quote = new Quote();
//
//        try {
//            String jsonData = response.body().string();
//
//            JSONObject quoteJSON = new JSONObject(jsonData);
//
//            String text = quoteJSON.getString("quoteText");
//            String author = quoteJSON.getString("quoteAuthor");
//
//
//           mQuote = new Quote(text, author);
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        return mQuote;
//    }
//}
