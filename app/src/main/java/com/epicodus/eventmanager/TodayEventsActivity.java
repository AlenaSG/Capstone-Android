package com.epicodus.eventmanager;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

import com.epicodus.eventmanager.util.OnStartDragListener;
import com.epicodus.eventmanager.util.SimpleItemTouchHelperCallback;
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


//////////////////////////////////////////////// FIRST VARIANT WITH STARTdragListener//////working display without Value event listener
//public class TodayEventsActivity extends AppCompatActivity implements OnStartDragListener {
//    private DatabaseReference mEventReference;
//    private FirebaseEventListAdapter mFirebaseAdapter;
//    private RecyclerView mRecyclerView;
//    private TextView mTvDateToday;
//    private ItemTouchHelper mItemTouchHelper;
//
//    private LinearLayoutManager mLayoutManager;//from CreateEventActivity
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
//        //Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);
//
//
//       Query query = FirebaseDatabase.getInstance().getReference("events").child(uid).orderByChild("date").equalTo(currentDate);
//
/////still shows events from the whole list in detail event pager - create a new array?
//        //deletes events ok from detail pager view
//
//        mFirebaseAdapter = new FirebaseEventListAdapter(Event.class,
//                R.layout.event_list_item_drag, FirebaseEventViewHolder.class,
//                query, this, this);
//
//        //Set the properties of the LinearLayoutManager
//        mLayoutManager = new LinearLayoutManager(this);
//
//
//        // And now set it to the RecyclerView
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        //no need to make new Adapter, there's one above
//        //mFirebaseAdapter = new EventListAdapter(getApplicationContext(), mEvents);//from this part in CreateEventActivity
//
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//
//        //include query here
//        //Toast.makeText(this, "JUST BEFORE set adapter", Toast.LENGTH_SHORT).show();
//        //setUpFirebaseAdapter();
//    }

   //private void setUpFirebaseAdapter() {

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mEventReference = FirebaseDatabase
//                .getInstance()
//                .getReference("events")
//                .child(uid);



        //WHY doesnt it work?
//
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);

/////////////code from CreateEventActivity Starts
        // Set the properties of the LinearLayoutManager
      //  mLayoutManager = new LinearLayoutManager(this);


        // And now set it to the RecyclerView

      //  mRecyclerView.setLayoutManager(mLayoutManager);
        //no need to make new Adapter, there's one above
       // mFirebaseAdapter = new EventListAdapter(getApplicationContext(), mEvents);//from this part in CreateEventActivity

       // mRecyclerView.setAdapter(mFirebaseAdapter);
        /////////////code from CreateEventActivity ends


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHelper.startDrag(viewHolder);
//    }
//
//
//
//
//}




/////////THIRD TRY/ not working
//
//public class TodayEventsActivity extends AppCompatActivity implements OnStartDragListener {
//    private DatabaseReference mEventReference;
//    private FirebaseEventListAdapter mFirebaseAdapter;
//    private ItemTouchHelper mItemTouchHelper;
//    private RecyclerView mRecyclerView;
//    private LinearLayoutManager mLayoutManager;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_today_events);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mEventReference = FirebaseDatabase
//                .getInstance()
//                .getReference("events")
//                .child(uid);
//
//        mFirebaseAdapter = new FirebaseEventListAdapter(Event.class,
//                R.layout.event_list_item_drag, FirebaseEventViewHolder.class,
//                mEventReference, this, this);
//        mRecyclerView.setHasFixedSize(true);
//         //Set the properties of the LinearLayoutManager
//        mLayoutManager = new LinearLayoutManager(this);
//       // mLayoutManager.setReverseLayout(true);
//        //mLayoutManager.setStackFromEnd(true);
//
//        // And now set it to the RecyclerView
//
//        mRecyclerView.setLayoutManager(mLayoutManager);
//       // mAdapter = new EventListAdapter(getApplicationContext(), mEvents);
//
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//
//
////        mRecyclerView.setHasFixedSize(true);
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
////        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//        mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//
//    @Override
//    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//        mItemTouchHelper.startDrag(viewHolder);
//    }
//}



////////////////////////?SECOND VARIANT////////// wih valueEventlistener//working fine  - before animation
public class TodayEventsActivity extends AppCompatActivity {
    private static final String TAG = "TodayEventsActivity";


    private TextView mTvDateToday;
    private TextView mTvHowManyEvents;
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
        mTvHowManyEvents = (TextView) findViewById(R.id.tvHowManyEvents);
        mTvDateToday = (TextView) findViewById(R.id.tvDateToday);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat ss = new SimpleDateFormat("M/dd/yyyy");///or double M
        Date date = new Date();
        String currentDate = ss.format(date);
        mTvDateToday.setText("Today is " + currentDate);
        databaseEvents = FirebaseDatabase.getInstance().getReference("events");

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
                    mTvHowManyEvents.setText("Nothing is planned for today");
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






//////////////////////////////////////////////// FIRST VARIANT//////working display without Value event listener
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