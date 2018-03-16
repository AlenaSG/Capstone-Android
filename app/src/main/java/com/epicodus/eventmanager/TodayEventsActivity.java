package com.epicodus.eventmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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



//mTvDateToday = (TextView) findViewById(R.id.tvDateToday);
//
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat ss = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//        Date date = new Date();
//        String currentDate = ss.format(date);
//        mTvDateToday.setText("Today is " + currentDate);

//public class TodayEventsActivity extends AppCompatActivity {
//    private DatabaseReference mReference;
//    private EventListAdapter mAdapter;
//
//    private RecyclerView mRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_today_events);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mReference = FirebaseDatabase
//                .getInstance()
//                .getReference("events")
//                .child(uid);
//
//        setUpFirebaseAdapter();
//    }
//
//    private void setUpFirebaseAdapter() {
//        mAdapter = new EventListAdapter<Event, EventViewHolder>(Event.class, R.layout.event_list_item, EventViewHolder.class,
//                        mReference) {
//
//            @Override
//            protected void populateViewHolder(EventViewHolder viewHolder,
//                                              Event model, int position) {
//                viewHolder.bindEvent(model);
//            }
//        };
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mAdapter);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mAdapter.cleanup();
//    }
//}


//    private static final String TAG = "TodayEventsActivity";
//
//    private TextView mTvDateToday;
//
//
//    private EventListAdapter mAdapter;
//
//    private RecyclerView mRecyclerView;
//    RecyclerView recyclerView;
//
//    //RecyclerView.Adapter adapter;
//
//    DatabaseReference databaseReference;
//
//    ProgressDialog progressDialog;
//    //private LinearLayoutManager mLayoutManager;
//
//    public ArrayList<Event> mEvents = new ArrayList<>();
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_today_events);
//
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(false);
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(layoutManager);
//
//       // Query query = mMessageReference.limitToLast(8);
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Toast.makeText(TodayEventsActivity.this, dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(TodayEventsActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAdapter = new EventListAdapter(Event.class, R.layout.fragment_today_events, EventViewHolder.class, databaseReference) {
//
//           @Override
//            protected void populateViewHolder(final EventViewHolder viewHolder, final Event event, final int position) {
//               viewHolder.bindEvent(event);
//            }
//
//        };
//
//        mRecyclerView.setAdapter(mAdapter);
//
//    }
//}
//

//            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//            recyclerView.setHasFixedSize(true);
//
//            recyclerView.setLayoutManager(new LinearLayoutManager(TodayEventsActivity.this));
//
////            progressDialog = new ProgressDialog(TodayEventsActivity.this);
////
////            progressDialog.setMessage("Loading Data from Firebase Database");
////
////            progressDialog.show();
//
//            databaseReference = FirebaseDatabase.getInstance().getReference("events");
//
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot snapshot) {
//
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//                        Event event = dataSnapshot.getValue(Event.class);
//
//                        mEvents.add(event);
//                    }
//
//                    mAdapter = new EventListAdapter(TodayEventsActivity.this, mEvents);
//
//                    recyclerView.setAdapter(mAdapter);
//
////                    progressDialog.dismiss();
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
////                    progressDialog.dismiss();
//                    Toast.makeText(TodayEventsActivity.this, "database error", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//
//        }
   // }


    

 

    