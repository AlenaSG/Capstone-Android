package com.epicodus.eventmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FindEventActivity extends AppCompatActivity {

    private static final String TAG = "FindEventActivity";
    private TextView mTvDateToday;
    private TextView mTvHowManyEvents;
    private RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public ArrayList<Event> mEvents = new ArrayList<>();

    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_today_events);

        mTvDateToday = (TextView) findViewById(R.id.tvDateToday);
        mTvHowManyEvents = (TextView) findViewById(R.id.tvHowManyEvents);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        mTvDateToday.setText("Events for " + date);


        mLayoutManager = new LinearLayoutManager(FindEventActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").equalTo(date);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mEvents.clear();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                            Event event = eventSnapshot.getValue(Event.class);

                    mEvents.add(event);
                }

                if (mEvents.size() == 0) {
                    mTvHowManyEvents.setText("Nothing is planned for this day");
                }
                mRecyclerView.setAdapter(mAdapter);

                if (mEvents.size() == 1)
                { Snackbar.make(findViewById(R.id.myLinearLayout), mEvents.size() + " event found",
                        Snackbar.LENGTH_LONG)
                        .show();
                } else
                { Snackbar.make(findViewById(R.id.myLinearLayout), mEvents.size() + " events found",
                        Snackbar.LENGTH_LONG)
                        .show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
