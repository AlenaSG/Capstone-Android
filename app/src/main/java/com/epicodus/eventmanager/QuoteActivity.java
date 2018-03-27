package com.epicodus.eventmanager;

import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.tls.OkHostnameVerifier;

import com.epicodus.eventmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

//public class QuoteActivity extends AppCompatActivity {
//    private static final String TAG = "QuoteActivity";
//
//    //public Object <Quote> quote = new Object <>;
//
//    TextView mAuthor;
//    TextView mText;
//    Button mBtnQuote;
//    //public quote Quote;
//    private OkHttpClient client;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_quote);
//
//        mText = (TextView) findViewById(R.id.tvText);
//        mAuthor = (TextView) findViewById(R.id.tvAuthor);
//        mBtnQuote = (Button) findViewById(R.id.btnQuote);
//
//        mBtnQuote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getQuote();
//            }
//        });
//
//        client = new OkHttpClient();
//
//    }// end of onCreate
//
//    private void getQuote() {
//        final Request request = new Request.Builder().url("http://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json").build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mText.setText("Failure!");
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, final Response response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            String jsonData = response.body().string();
//
//                            JSONObject quoteJSON = new JSONObject(jsonData);
//
//                            String text = quoteJSON.getString("quoteText");
//                            String author = quoteJSON.getString("quoteAuthor");
//
//                            mText.setText(text);
//                            mAuthor.setText(author);
//                        }catch (JSONException e) {
//                                throw new RuntimeException(e);
//                        } catch (IOException ioe) {
//                            mText.setText("Error during get body");
//                        }
//                    }
//                });
//            }
//        });
//    }
//}
