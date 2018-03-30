package com.epicodus.eventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;


public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    private Button mTodayBtn;
    private Button mWeekBtn;
    private Button mMonthBtn;
    private Button mAllBtn;
    private Button mAllPastBtn;
    private Button mNextMonthBtn;

    public String mDateToday;
    public FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                String date = (month + 1) + "/" + day + "/" + year;
                int currentMonth = month+1;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy " + date + ", " + month);
                mDateToday = date;
                Intent intent = new Intent(CalendarActivity.this, FindEventActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

        mTodayBtn = (Button) findViewById(R.id.todayBtn);
        mWeekBtn = (Button) findViewById(R.id.weekBtn);
        mMonthBtn = (Button) findViewById(R.id.monthBtn);
        mAllBtn = (Button) findViewById(R.id.allBtn);
        mNextMonthBtn = (Button) findViewById(R.id.nextMonthBtn);
        mAllPastBtn = (Button) findViewById(R.id.allPastBtn);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalendarActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        mTodayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(CalendarActivity.this, "TODAY EVENTS", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(CalendarActivity.this, TodayEventsActivity.class);
                //intent.putExtra("todayDate", mDateToday);
                startActivity(intent);

//                Fragment fragment = new TodayEventsFragment();
//
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, fragment);
//                transaction.commit();

            }
        });

        mWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "NEXT 7 DAYS EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, WeekEventsActivity.class);
                startActivity(intent);
            }
        });

        mMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "THIS MONTH EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, MonthEventsActivity.class);
                startActivity(intent);
            }
        });

        mNextMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "NEXT MONTH EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, NextMonthEventsActivity.class);
                startActivity(intent);
            }
        });


        mAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "ALL FUTURE EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, AllFutureEventsActivity.class);
                startActivity(intent);
            }
        });

        mAllPastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "ALL PAST EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, AllPastEventsActivity.class);
                startActivity(intent);
            }
        });


    }
}
