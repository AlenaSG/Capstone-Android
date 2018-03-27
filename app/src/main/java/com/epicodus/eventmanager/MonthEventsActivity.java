package com.epicodus.eventmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthEventsActivity extends AppCompatActivity {

    private static final String TAG = "MonthEventsActivity";

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

        Calendar calendar = Calendar.getInstance();
        String currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        final int currentMonthDigit = Calendar.getInstance().get(Calendar.MONTH);
        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
        Date date = new Date();
        String currentDate = ss.format(date);
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        mTvDateToday.setText("Today is " + currentDate + "\n" + "Events for "+ currentMonth + "(" + (currentMonthDigit+1) + "), " + currentYear);
        //databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        databaseEvents = FirebaseDatabase.getInstance().getReference();
        // Write a message to the database

        //http://javarevisited.blogspot.com/2012/12/how-to-convert-millisecond-to-date-in-java-example.html

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(MonthEventsActivity.this);
        // mLayoutManager.setReverseLayout(true);
        //mLayoutManager.setStackFromEnd(true);

        // And now set it to the RecyclerView

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").startAt(currentDate);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mEvents.clear();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    Event event = eventSnapshot.getValue(Event.class);

                    long eventMillis = event.getMillis();
                    android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
                    calendar.setTimeInMillis(eventMillis);
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    //String dateOfEvent = formatter.format(calendar.getTime());
                    int eventYear = calendar.get(Calendar.YEAR);
                    int eventMonth = calendar.get(Calendar.MONTH);
                    //Log.d(TAG, "onDataChange: PRINTING DATE" + dateOfEvent);
                    Log.d(TAG, "onDataChange: MONTH" + eventMonth);
                    Log.d(TAG, "onDataChange: YEAR" + eventYear);
                    if (eventYear == currentYear && eventMonth == currentMonthDigit) {
                        mEvents.add(event);
                    }
                    if (mEvents.size() == 0) {
                        mTvHowManyEvents.setText("Nothing is planned for this month");
                    }
                }

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}