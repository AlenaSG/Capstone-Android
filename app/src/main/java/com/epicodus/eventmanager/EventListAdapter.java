package com.epicodus.eventmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>{
    private ArrayList<Event> mEvents = new ArrayList<>();
    private Context mContext;

    public EventListAdapter(Context context, ArrayList<Event> events) {
        mContext = context;
        mEvents = events;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventViewHolder holder, int position) {
        holder.bindEvent(mEvents.get(position));
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }
    public class EventViewHolder extends RecyclerView.ViewHolder {
        private ImageView mEventImageView;
        private TextView mNameTextView;
        private TextView mTypeTextView;


        private Context mContext;

        public EventViewHolder(View itemView) {
            super(itemView);
            mEventImageView = (ImageView) itemView.findViewById(R.id.eventImageView);
            mNameTextView = (TextView) itemView.findViewById(R.id.eventNameTextView);
            mTypeTextView = (TextView) itemView.findViewById(R.id.typeTextView);

            mContext = itemView.getContext();
        }

        public void bindEvent(Event event) {
            mNameTextView.setText(event.getDescription());
            mTypeTextView.setText(event.getType());
        }
    }
}
