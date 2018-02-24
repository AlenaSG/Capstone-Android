package com.epicodus.eventmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.epicodus.eventmanager.Event;

import org.parceler.Parcels;


public class EventDetailFragment extends Fragment {
    private static final String TAG = "EventDetailFragment";

    ImageView mImageLabel;
    TextView mNameLabel;
    TextView mTypeLabel;
    TextView mRatingLabel;
    TextView mDetails;
    TextView mDateLabel;
    TextView mTimeLabel;
    TextView mAddressLabel;
    Button mAddToCalendarButton;

    private Event mEvent;

    public static EventDetailFragment newInstance(Event event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        Log.d(TAG, "newInstance:6666666666 ");
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        Log.d(TAG, "newInstance: 00000000: can wrap an instance of eventDetailFragment!!!!");
        return eventDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
        Log.d(TAG, "onCreate1111: can unwrap an instance of eventDetailFragment!!!!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        mImageLabel = (ImageView) view.findViewById(R.id.eventImageView);
        mNameLabel = (TextView) view.findViewById(R.id.eventNameTextView);
        mTypeLabel = (TextView) view.findViewById(R.id.typeTextView);
        mRatingLabel = (TextView) view.findViewById(R.id.ratingTextView);
        mDetails = (TextView) view.findViewById(R.id.detailsTextView);
        mDateLabel = (TextView) view.findViewById(R.id.dateTextView);
        mTimeLabel = (TextView) view.findViewById(R.id.timeTextView);
        mAddressLabel = (TextView) view.findViewById(R.id.addressTextView);
        mAddToCalendarButton = (Button) view.findViewById(R.id.addToCalendarButton);

        //Picasso.with(view.getContext()).load(mRestaurant.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mEvent.getName());
        mTypeLabel.setText(mEvent.getType());
        //mRatingLabel.setText(Double.toString(event.getRating()) + "/5");
        mDateLabel.setText(mEvent.getDate());
        mTimeLabel.setText(mEvent.getTime());
        mAddressLabel.setText(mEvent.getAddress());
       // mRatingLabel.setText("rating");
        //mDetails.setText("details");
        Log.d(TAG, "onCreateView222: can create instance of eventDetailFragment!!!!");

        return view;
    }

}
