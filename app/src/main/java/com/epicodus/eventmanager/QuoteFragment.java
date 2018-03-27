package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuoteFragment extends Fragment {
    private static final String TAG = "QuoteFragment";

    //public Object <Quote> quote = new Object <>;

    TextView mAuthor;
    TextView mText;
    Button mBtnQuote;
    //Button mBtnCreateEvent;
    //public quote Quote;
    private OkHttpClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quote, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // mTvSettings = (TextView) view.findViewById(R.id.tvSettings);
        mText = (TextView) view.findViewById(R.id.tvText);
        mAuthor = (TextView) view.findViewById(R.id.tvAuthor);
        mBtnQuote = (Button) view.findViewById(R.id.btnQuote);
        //mBtnCreateEvent = (Button) view.findViewById(R.id.btnCreateEvent);

        //view.findViewById(R.id.btnCreateEvent).setEnabled(false);

        view.findViewById(R.id.btnQuote).setOnClickListener(new View.OnClickListener() {//crash here - check the Quote Activity
            @Override
            public void onClick(View view) {
                getQuote();

            }
        });
        client = new OkHttpClient();

    }
    private void getQuote() {
        final Request request = new Request.Builder().url("http://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mText.setText("Failure!");
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String jsonData = response.body().string();

                            JSONObject quoteJSON = new JSONObject(jsonData);

                            String text = quoteJSON.getString("quoteText");
                            String author = quoteJSON.getString("quoteAuthor");

                            mText.setText(text);
                            mAuthor.setText(author);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        } catch (IOException ioe) {
                            mText.setText("Error during get body");
                        }
                    }
                });
            }
        });
    }


}// end of fragment



//public class QuoteFragment extends Fragment {
//    TextView mTvSettings;
//    //Button mBtnQuote;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_quote, null);
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mTvSettings = (TextView) view.findViewById(R.id.tvSettings);
//
//        view.findViewById(R.id.btnQuote).setOnClickListener(new View.OnClickListener() {//crash here - check the Quote Activity
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "You are inside fragment quote", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), QuoteActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}
