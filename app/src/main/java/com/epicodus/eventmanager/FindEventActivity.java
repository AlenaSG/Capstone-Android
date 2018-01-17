package com.epicodus.eventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FindEventActivity extends AppCompatActivity {

    private static final String TAG = "FindEventActivity";

    private Button btnGoToCalendar;
    private Button btnChooseDate;
    private TextView theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_find);

        theDate = (TextView) findViewById(R.id.tvDate);
        btnGoToCalendar = (Button) findViewById(R.id.btnGoToCalendar);

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnGoToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindEventActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
