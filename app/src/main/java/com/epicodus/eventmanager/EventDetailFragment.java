package com.epicodus.eventmanager;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

//import com.epicodus.eventmanager.Event;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    android.icu.util.Calendar mNewChosenDate = android.icu.util.Calendar.getInstance();

    //mNewChosenDate = android.icu.util.Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    private static final int REQUEST_IMAGE_CAPTURE = 111;

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
        setHasOptionsMenu(true);
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
        mDateLabel.setText(mEvent.getDate());
        mTimeLabel.setText(mEvent.getTime());
        mAddressLabel.setText(mEvent.getAddress());

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String eventID = mEvent.getEventID();

        if (id == R.id.action_delete) {
            //showUpdateDialog(eventName);
            // Toast.makeText(getContext(), "Write some delete code", Toast.LENGTH_SHORT).show();
            Toast.makeText(getContext(), eventID, Toast.LENGTH_SHORT).show();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference databaseEvents = FirebaseDatabase.getInstance().getReference("events").child(uid).child(eventID);

            databaseEvents.removeValue();

            Toast.makeText(getContext(), "Event is deleted", Toast.LENGTH_LONG).show();


            ///change to if sourse is .... then go to.....
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_update) {
            //showDeleteDialog(eventName);
            Toast.makeText(getContext(), eventID, Toast.LENGTH_SHORT).show();
            showUpdateDialog(mEvent.getEventID(), mEvent.getName());

            return true;
        }
        return super.onOptionsItemSelected(item);

    }


    ////from Stack overflow https://stackoverflow.com/questions/41200876/how-to-set-onitemclicklistener-for-recyclerview
//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//        public void onClick(View view, int position) {
//
//                Event event = events.get(position);
//
//                Intent intent = new Intent(getApplicationContext(), AddRatingActivity.class);
//
//                intent.putExtra(EVENT_ID, event.getEventID());
//                intent.putExtra(EVENT_NAME, event.getName());
//
//                startActivity(intent);
//        }
//
//        @Override
//        public void onLongClick(View view, int position) {
//            Event event = events.get(position);
//
//               showUpdateDialog(event.getEventID(), event.getName());
//            Toast.makeText(CreateEventActivity.this, "long click", Toast.LENGTH_SHORT).show();
//        }
//    }));


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        //if (mSource.equals("saved")) {
//            inflater.inflate(R.menu.menu_photo, menu);
////        } else {
////            inflater.inflate(R.menu.menu_main, menu);
////        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_photo:
//                onLaunchCamera();
//            default:
//                break;
//        }
//        return false;
//    }
//
//
//    public void onLaunchCamera() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }


    //////update, delete
    private void showUpdateDialog(final String eventID, String eventName) {

       // mEvent.getEventID(), mEvent.getName(), mEvent.getDate(),
               // mEvent.getTime(), mEvent.getMillis(), mEvent.getType(), mEvent.getAddress()
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText etName = (EditText) dialogView.findViewById(R.id.etNewName);
        final TextView tvDate = (TextView) dialogView.findViewById(R.id.tvNewDate);
        final TextView tvTime = (TextView) dialogView.findViewById(R.id.tvNewTime);
        final TextView tvMillis = (TextView) dialogView.findViewById(R.id.tvNewMillis);
        final EditText etAddress = (EditText) dialogView.findViewById(R.id.etNewAddress);
        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Spinner spnUpdate = (Spinner) dialogView.findViewById(R.id.spnUpdate);
        final Button btnDismiss = (Button) dialogView.findViewById(R.id.btnDismiss);
        final Button btnSetDateTime = (Button) dialogView.findViewById(R.id.btnSelectDate);

        dialogBuilder.setTitle("Update Event - " + eventName);

        etName.setText(mEvent.getName());
        tvDate.setText(mEvent.getDate());
        tvTime.setText(mEvent.getTime());
        //tvMillis.setHint.(mEvent.getMillis()); //cannot set text to long
        etAddress.setText(mEvent.getAddress());

        int index;
        if (mEvent.getType().equals("Birthday")) {///replace if with switch
            index = 0;
        } else if (mEvent.getType().equals("Show")) {
            index = 1;
        } else if (mEvent.getType().equals("Class")) {
            index = 2;
        } else {
            index = 3;
        }

        spnUpdate.setSelection(index);


        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        //String eventid = mEvent.getEventID();

        btnSetDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                int theYear = calendar.get(Calendar.YEAR);
                int theMonth = calendar.get(Calendar.MONTH);
                int theDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        theYear,theMonth,theDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                mNewChosenDate.set(Calendar.HOUR_OF_DAY, hour);
                mNewChosenDate.set(Calendar.MINUTE, min);

// TODO: 3/2/18 make format - double digit ex. 02:04 not 2:4
                String timePicked = hour + ":" + min;
                tvTime.setText(timePicked);

            }
        };

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int theYear, int theMonth, int theDay) {

                String datePicked = (theMonth + 1) + "/" + theDay + "/" + theYear;

                tvDate.setText(datePicked);

                mNewChosenDate.set(Calendar.YEAR, theYear);
                mNewChosenDate.set(Calendar.MONTH, theMonth);
                mNewChosenDate.set(Calendar.DAY_OF_MONTH, theDay);

                //show timePickerDialog
                Calendar c = Calendar.getInstance();
                int min = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR);

                TimePickerDialog dialog = new TimePickerDialog(
                        getContext(),
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mTimeSetListener,
                        hour,min, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        };



        btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = etName.getText().toString().trim();
            String type = spnUpdate.getSelectedItem().toString();
            String date = tvDate.getText().toString().trim();
            String time = tvTime.getText().toString().trim();
            long millis = mNewChosenDate.getTimeInMillis(); ///check if millis updated correctly
            String address = etAddress.getText().toString().trim();
            //String eventID = mEvent.getEventID();

            Log.d(TAG, "AddEvent: current millis=" + millis + ", date=" + date + ", time=" + time);
            android.icu.util.Calendar calendar = android.icu.util.Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            Log.d(TAG, "AddEvent: parsed datetime=" + calendar.getTime().toString());

            if(TextUtils.isEmpty(name)){//add time etc.
                etName.setError("Name is required");
                return;
            }
            updateEvent(eventID, name, type, date, time, millis, address);

            alertDialog.dismiss();


            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
        }
    });



    }//show update dialog

    private boolean updateEvent(String id, String name, String type, String date, String time, long millis, String address){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events").child(uid).child(id);
        DatabaseReference pushRef = databaseReference.push();
        //String eventID = pushRef.getKey();


        Event event = new Event(name, type, date, time, millis, address);


        event.setEventID(id);
        pushRef.setValue(event);

        databaseReference.setValue(event);

        Toast.makeText(getContext(), "Event updated successfully", Toast.LENGTH_LONG).show();

        return true;
    }
}







//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteEvent(eventID);
//                alertDialog.dismiss();
//            }
//
//        });
//    }
//
//    private void deleteEvent(String eventID) {
//        DatabaseReference drEvent = FirebaseDatabase.getInstance().getReference("events").child(eventID);
//        DatabaseReference drDetails = FirebaseDatabase.getInstance().getReference("details").child(eventID);
//
//        drEvent.removeValue();
//        drDetails.removeValue();
//
//        Toast.makeText(this, "Event is deleted", Toast.LENGTH_LONG).show();
//    }
//
//