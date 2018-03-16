package com.epicodus.eventmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.epicodus.eventmanager.EventListAdapter.EventViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TodayEventsActivity extends AppCompatActivity {
    private DatabaseReference mEventReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private RecyclerView mRecyclerView;
    private TextView mTvDateToday;


    private LinearLayoutManager mLayoutManager;//from CreateEventActivity



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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mEventReference = FirebaseDatabase
                .getInstance()
                .getReference("events")
                .child(uid);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder>(Event.class, R.layout.event_list_item_drag, FirebaseEventViewHolder.class, mEventReference) {

            @Override
            protected void populateViewHolder(FirebaseEventViewHolder viewHolder, Event model, int position) {
                viewHolder.bindEvent(model);
            }
        };

        //WHY doesnt it work?
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);

/////////////code from CreateEventActivity Starts
        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // And now set it to the RecyclerView

        mRecyclerView.setLayoutManager(mLayoutManager);
        //no need to make new Adapter, there's one above
       // mFirebaseAdapter = new EventListAdapter(getApplicationContext(), mEvents);//from this part in CreateEventActivity

        mRecyclerView.setAdapter(mFirebaseAdapter);
        /////////////code from CreateEventActivity ends
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}


 

    