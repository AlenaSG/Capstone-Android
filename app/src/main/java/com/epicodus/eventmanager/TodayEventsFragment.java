package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;


public class TodayEventsFragment extends Fragment {

    public ArrayList<Event> mEvents = new ArrayList<>();

    DatabaseReference databaseEvents;
    private RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;
    private Event mEvent;


//    public static TodayEventsFragment newInstance(Event event) {
//        TodayEventsFragment todayEventsFragment = new TodayEventsFragment();
//        Bundle args = new Bundle();
//        args.putParcelable("event", Parcels.wrap(event));
//        todayEventsFragment.setArguments(args);
//        return TodayEventsFragment;
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_today_events, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();

        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mEvents.clear();

                for(DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    Event event = eventSnapshot.getValue(Event.class);

                    mEvents.add(event);
                }

                //EventList adapter = new EventList(CreateEventActivity.this, mEvents);

                //to display event immediately after creation
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
