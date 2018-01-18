package com.epicodus.eventmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class FindEventActivity extends AppCompatActivity {

    private static final String TAG = "FindEventActivity";

    private Button btnGoToCalendar;
    private Button btnSelectDate;
    private TextView theDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_find);

        theDate = (TextView) findViewById(R.id.tvDate);
        btnGoToCalendar = (Button) findViewById(R.id.btnGoToCalendar);
        btnSelectDate = (Button) findViewById(R.id.btnSelectDate);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int theYear = cal.get(Calendar.YEAR);
                int theMonth = cal.get(Calendar.MONTH);
                int theDay = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FindEventActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        theYear,theMonth,theDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int theYear, int theMonth, int theDay) {
                theMonth = theMonth + 1;

                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + theMonth + "/" + theDay + theYear);

                String datePicked = theMonth + "/" + theDay + "/" + theYear;
                theDate.setText(datePicked);
            }
        };

        btnGoToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindEventActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });


    }
}
