package com.epicodus.eventmanager;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.epicodus.eventmanager.Event;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class EventDetailFragment extends Fragment implements View.OnClickListener {
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
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = Parcels.unwrap(getArguments().getParcelable("event"));
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

        mAddressLabel.setOnClickListener(this);
        mAddToCalendarButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mEvent.getAddress()));
            startActivity(mapIntent);
        }
        //Note: This intent for a calendar event is supported only with API level 14 and higher.
        //https://developer.android.com/training/basics/intents/sending.html
        //https://stackoverflow.com/questions/14694931/insert-event-to-calendar-using-intent

        if (v == mAddToCalendarButton) {
            //Calendar beginTime = Calendar.getInstance();
            //this data can have 01 or 1 for minutes
            //beginTime.set(2018, 1, 28, 14, 1);
            //Calendar endTime = Calendar.getInstance();
            //endTime.set(2018, 1, 28, 8, 30);
            Intent calendarIntent = new Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI);
           // calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, mEvent.getDate());//returns todays date
            //calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            //calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());

            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, mEvent.getMillis());
            calendarIntent.putExtra(CalendarContract.Events.TITLE, mEvent.getName());
            calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, mEvent.getAddress());

            startActivity(calendarIntent);
        }
    }

}
