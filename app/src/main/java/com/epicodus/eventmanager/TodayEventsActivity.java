package com.epicodus.eventmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TodayEventsActivity extends AppCompatActivity {

    private TextView mTvDateToday;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_today_events);

        mTvDateToday = (TextView)findViewById(R.id.tvDateToday);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date date = new Date();
        String currentDate= ss.format(date);
        mTvDateToday.setText("Today is " + currentDate);

    }
}
