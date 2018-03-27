package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.eventmanager.util.ItemTouchHelperAdapter;
import com.epicodus.eventmanager.util.OnStartDragListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;


public class FirebaseEventListAdapter extends FirebaseRecyclerAdapter<Event, FirebaseEventViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ChildEventListener mChildEventListener;
    private ArrayList<Event> mEvents = new ArrayList<>();
//    private int mOrientation;


    public FirebaseEventListAdapter(Class<Event> modelClass, int modelLayout,
                                         Class<FirebaseEventViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mEvents.add(dataSnapshot.getValue(Event.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //there is remove code below
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void populateViewHolder(final FirebaseEventViewHolder viewHolder, Event model, int position) {
        viewHolder.bindEvent(model);
//        mOrientation = viewHolder.itemView.getResources().getConfiguration().orientation;
//        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            createDetailFragment(0);

        viewHolder.mEventImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent eventM) {
                if (MotionEventCompat.getActionMasked(eventM) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
//
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // int itemPosition = viewHolder.getAdapterPosition();
//                if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//                    createDetailFragment(itemPosition);
//                } else {
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                //intent.putExtra("position", itemPosition + "");
                //intent.putExtra("eventName", viewHolder.mNameTextView.getText().toString());
                intent.putExtra("events", Parcels.wrap(mEvents));
                //intent.putExtra("source", SyncStateContract.Constants.SOURCE_SAVED);
                mContext.startActivity(intent);
            }
        });
    }
        //////////////////////// code from original viewholder
       // @Override
//    public void onClick(View view) {
//        final ArrayList<Event> events = new ArrayList<>();
//
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("events");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    events.add(snapshot.getValue(Event.class));
//                }
//
//                int itemPosition = getLayoutPosition();
//
//                Intent intent = new Intent(mContext, EventDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("events", Parcels.wrap(events));
//
//                mContext.startActivity(intent);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }


        ////////////////////////





//
//    private void createDetailFragment(int position){
//        EventDetailFragment detailFragment = EventDetailFragment.newInstance(mEvents, position, SyncStateContract.Constants.SOURCE_SAVED);
//        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.eventDetailContainer, detailFragment);
//        ft.commit();
   // }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mEvents, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        ///include dialog - DELEte Event?
        mEvents.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Event event : mEvents) {

            int index = mEvents.indexOf(event);
            DatabaseReference ref = getRef(index);
            event.setIndex(Integer.toString(index));
            ref.setValue(event);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}