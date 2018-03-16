package com.epicodus.eventmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.eventmanager.util.OnStartDragListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


//public class TodayEventsFragment extends Fragment implements OnStartDragListener {
//    private RecyclerView mRecyclerView;
//
//    private FirebaseEventListAdapter mFirebaseAdapter;
//    private ItemTouchHelper mItemTouchHelper;
//
//    public TodayEventsFragment() {
//            // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_today_events, container, false);
//
//        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
//        setUpFirebaseAdapter();
//        return view;
//    }
//
//        private void setUpFirebaseAdapter() {
//            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            String uid = user.getUid();
//
//            Query query = FirebaseDatabase.getInstance()
//                    .getReference("events")
//                    .child(uid)
//                    .orderByChild("millis");
//
//            //  In line below, we change 6th parameter 'this' to 'getActivity()'
//            //  because fragments do not have own context:
//            mFirebaseAdapter = new FirebaseEventListAdapter(Event.class,
//
//                    R.layout.event_list_item_drag, FirebaseEventViewHolder.class,
//                    query, this, getActivity());
//
//            mRecyclerView.setHasFixedSize(true);
//
//            //In line below, we change 'this' to 'getActivity()' because fragments do not have own context:
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            mRecyclerView.setAdapter(mFirebaseAdapter);
//
//
//            mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//                @Override
//                public void onItemRangeInserted(int positionStart, int itemCount) {
//                    super.onItemRangeInserted(positionStart, itemCount);
//                    mFirebaseAdapter.notifyDataSetChanged();
//                }
//            });
//
//            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
//            mItemTouchHelper = new ItemTouchHelper(callback);
//            mItemTouchHelper.attachToRecyclerView(mRecyclerView);
//        }
//
//        @Override
//        public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
//            mItemTouchHelper.startDrag(viewHolder);
//        }
//
//        @Override
//        //method is now public
//        public void onDestroy() {
//            super.onDestroy();
//            mFirebaseAdapter.cleanup();
//        }
//    }
