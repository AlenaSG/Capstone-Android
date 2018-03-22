package com.epicodus.eventmanager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class QuoteService {
    public static void findQuote(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json").newBuilder();
        //urlBuilder.addQueryParameter("json", format);
        //String url = urlBuilder.build().toString();
        String url = urlBuilder.toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
