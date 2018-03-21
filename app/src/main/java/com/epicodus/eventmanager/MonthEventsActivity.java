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

/**
 * Created by alenagolovina on 3/20/18.
 */

public class MonthEventsActivity extends AppCompatActivity {

    private static final String TAG = "MonthEventsActivity";

    private TextView mTvDateToday;
    private RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static final String EVENT_NAME = "eventname";
    public static final String EVENT_ID = "eventid";
    //define view objects


    public ArrayList<Event> mEvents = new ArrayList<>();

    DatabaseReference databaseEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_today_events);

        mTvDateToday = (TextView) findViewById(R.id.tvDateToday);


        Calendar calendar = Calendar.getInstance();
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
        Date date = new Date();
        String currentDate = ss.format(date);
        mTvDateToday.setText("Today is " + currentDate + ", " + month);
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
                    Log.d(TAG, "onDataChange: HERE");
                    long eventMillis = event.getMillis();
                    android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
                    calendar.setTimeInMillis(eventMillis);
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");//but i only need month
                    String dateOfEvent = formatter.format(calendar.getTime());
                    //Date dateOfEvent = new Date(eventMillis);
                    Log.d(TAG, "onDataChange: PRINTING DATE" + dateOfEvent);

                    mEvents.add(event);
                }

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}