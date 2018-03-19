package com.epicodus.eventmanager.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.epicodus.eventmanager.Event;
import com.epicodus.eventmanager.EventListAdapter;
import com.epicodus.eventmanager.R;
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
import java.util.Calendar;
import java.util.Date;

public class AllFutureEventsActivity extends AppCompatActivity {
    private static final String TAG = "AllFutureEventsActivity";


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
        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
        Date date = new Date();
        String currentDate = ss.format(date);
        mTvDateToday.setText("Today is " + currentDate);
        mTvDateToday.setText("All Future Events");
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        // Write a message to the database

        //http://javarevisited.blogspot.com/2012/12/how-to-convert-millisecond-to-date-in-java-example.html


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(AllFutureEventsActivity.this);
        // mLayoutManager.setReverseLayout(true);
        //mLayoutManager.setStackFromEnd(true);

        // And now set it to the RecyclerView

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference databaseEvents = FirebaseDatabase
                .getInstance()
                .getReference("events")
                .child(uid);

        Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").startAt(currentDate);///to display all events - no old ones
//        Query query = FirebaseDatabase.getInstance()
//                    .getReference("events")
//                    .child(uid)
//                    .orderByChild("millis");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mEvents.clear();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    Event event = eventSnapshot.getValue(Event.class);

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
