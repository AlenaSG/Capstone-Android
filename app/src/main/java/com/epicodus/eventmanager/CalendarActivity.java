package com.epicodus.eventmanager;

import android.content.Intent;
import android.os.Bundle;
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

    public String mDateToday;


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
                Toast.makeText(CalendarActivity.this, "WEEK EVENTS", Toast.LENGTH_SHORT).show();
            }
        });

        mMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "MONTH EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, MonthEventsActivity.class);
                //intent.putExtra("todayDate", mDateToday);
                startActivity(intent);
            }
        });


        mAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, "ALL FUTURE EVENTS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CalendarActivity.this, AllFutureEventsActivity.class);
                //intent.putExtra("todayDate", mDateToday);
                startActivity(intent);
            }
        });

    }
}
