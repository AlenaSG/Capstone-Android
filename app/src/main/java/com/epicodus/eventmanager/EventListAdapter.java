package com.epicodus.eventmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

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


    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mEventImageView;
        private TextView mNameTextView;
        private TextView mTypeTextView;
        private TextView mDateTextView;
        private TextView mTimeTextView;
        private TextView mAddressTextView;

        private Context mContext;

        public EventViewHolder(View itemView) {
            super(itemView);
            mEventImageView = (ImageView) itemView.findViewById(R.id.eventImageView);
            mNameTextView = (TextView) itemView.findViewById(R.id.eventNameTextView);
            mTypeTextView = (TextView) itemView.findViewById(R.id.typeTextView);
            mDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            mTimeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            mAddressTextView = (TextView) itemView.findViewById(R.id.addressTextView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindEvent(Event event) {

            //Picasso


            mNameTextView.setText(event.getName());
            mTypeTextView.setText(event.getType());

            String birthday = "Birthday";
            String show = "Show";
            //word "class" is taken
            String other = "Other";

            if (mTypeTextView.getText().equals(birthday)) {
                mEventImageView.setImageResource(R.drawable.birthday_cupcake);
            } else if (mTypeTextView.getText().equals(show)) {
                mEventImageView.setImageResource(R.drawable.show_curtains);
            }
            if (mTypeTextView.getText().equals("Class")) {
                mEventImageView.setImageResource(R.drawable.class_hands);
            }

            mDateTextView.setText(event.getDate());
            mTimeTextView.setText("Starts at: " + event.getTime());
            mAddressTextView.setText(event.getAddress());
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();

            Intent intent = new Intent(mContext, EventDetailActivity.class);
            intent.putExtra("position", itemPosition + "");
            intent.putExtra("events", Parcels.wrap(mEvents));
            mContext.startActivity(intent);
        }

    }
}
