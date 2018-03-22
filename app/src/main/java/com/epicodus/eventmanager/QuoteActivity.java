package com.epicodus.eventmanager;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import okhttp3.Response;
import com.epicodus.eventmanager.R;

public class QuoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Toast.makeText(QuoteActivity.this, "AAAAAAAAA", Toast.LENGTH_SHORT).show();
        getQuote();
    }

    private void getQuote() {
        final QuoteService quoteService = new QuoteService();
        quoteService.findQuote(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    //Toast.makeText(QuoteActivity.this, jsonData, Toast.LENGTH_SHORT).show();
                    Toast.makeText(QuoteActivity.this, "kukuku", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
