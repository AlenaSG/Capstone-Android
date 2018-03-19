package com.epicodus.eventmanager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.LayoutInflater;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.epicodus.eventmanager.EventListAdapter.EventViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TodayEventsActivity extends AppCompatActivity {
    private static final String TAG = "TodayEventsActivity";


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
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        // Write a message to the database

        //http://javarevisited.blogspot.com/2012/12/how-to-convert-millisecond-to-date-in-java-example.html


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(TodayEventsActivity.this);
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

       Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").equalTo(currentDate);//display todays events
        //Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").startAt(currentDate);///to display all events - no old ones
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






//////////////////////////////////////////////// working display without Value event listener
//public class TodayEventsActivity extends AppCompatActivity {
//    private DatabaseReference mEventReference;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//
//    private RecyclerView mRecyclerView;
//    private TextView mTvDateToday;
//
//
//    private LinearLayoutManager mLayoutManager;//from CreateEventActivity
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_today_events);
//        mTvDateToday = (TextView) findViewById(R.id.tvDateToday);
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
//        Date date = new Date();
//        String currentDate = ss.format(date);
//        mTvDateToday.setText("Today is " + currentDate);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mEventReference = FirebaseDatabase
//                .getInstance()
//                .getReference("events")
//                .child(uid);
//
//        //include query here
//
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder>(Event.class, R.layout.event_list_item_drag, FirebaseEventViewHolder.class, mEventReference) {
//
//            @Override
//            protected void populateViewHolder(FirebaseEventViewHolder viewHolder, Event model, int position) {
//                viewHolder.bindEvent(model);
//            }
//        };
//
//        //WHY doesnt it work?
////
////        mRecyclerView.setHasFixedSize(true);
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
////        mRecyclerView.setAdapter(mFirebaseAdapter);
//
///////////////code from CreateEventActivity Starts
//        // Set the properties of the LinearLayoutManager
//        mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
//
//        // And now set it to the RecyclerView
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        //no need to make new Adapter, there's one above
//       // mFirebaseAdapter = new EventListAdapter(getApplicationContext(), mEvents);//from this part in CreateEventActivity
//
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//        /////////////code from CreateEventActivity ends
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//}
//
//
//
//
//