package com.epicodus.eventmanager;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.Calendar;
import java.util.Date;


//https://stackoverflow.com/questions/7654151/how-to-add-7-days-to-current-date-while-not-going-over-available-days-of-a-month
public class WeekEventsActivity extends AppCompatActivity {
    private static final String TAG = "WeekEventsActivity";


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
        mTvHowManyEvents = (TextView) findViewById(R.id.tvHowManyEvents);
        mTvDateToday = (TextView) findViewById(R.id.tvDateToday);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
        Date date = new Date();


//
//        Date m = new Date();
//        Calendar cal = Calendar.getInstance();
        //calendar.setTime(m);
         calendar.add(Calendar.DATE, 7); // 7 is the days you want to add or subtract
        //m = cal.getTime();
       // System.out.println(m);



        String currentDate = ss.format(date);
        mTvDateToday.setText("Today is " + currentDate);
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(WeekEventsActivity.this);
        // mLayoutManager.setReverseLayout(true);
        //mLayoutManager.setStackFromEnd(true);

        // And now set it to the RecyclerView

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").equalTo(currentDate);//display todays events

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mEvents.clear();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    Event event = eventSnapshot.getValue(Event.class);

                    mEvents.add(event);
                }

                if (mEvents.size() == 0) {
                    mTvHowManyEvents.setText("Nothing is planned for  the next 7 days");
                }
                mRecyclerView.setAdapter(mAdapter);

                Snackbar.make(findViewById(R.id.myLinearLayout), mEvents.size() + " events found",
                        Snackbar.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

