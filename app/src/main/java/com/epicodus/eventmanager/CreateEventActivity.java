package com.epicodus.eventmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Date;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.recyclerview.R.attr.layoutManager;
import static com.epicodus.eventmanager.R.id.recyclerView;
import static com.epicodus.eventmanager.R.id.theTimePicker;

public class CreateEventActivity extends AppCompatActivity {
    private static final String TAG = "CreateEventActivity";

    private RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    public static final String EVENT_NAME = "eventname";
    public static final String EVENT_ID = "eventid";
    //define view objects
    private EditText mNameET;
    private EditText mAddressET;
    private Button mSaveEventBtn;
    private Spinner mSelectTypeSpn;
    private Button mSelectDateBtn;
    private TextView mTheDate;
    private TextView mTheTime;
    private Button mSelectTimeBtn;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    public ArrayList<Event> mEvents = new ArrayList<>();

    DatabaseReference databaseEvents;
    Calendar mChosenDate = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        databaseEvents = FirebaseDatabase.getInstance().getReference("events").child(uid);
        // Write a message to the database

        mNameET = (EditText) findViewById(R.id. etName);
        mAddressET = (EditText) findViewById(R.id. etAddress);
        mSelectTypeSpn = (Spinner) findViewById(R.id.spnSelectType);
        mSelectDateBtn = (Button) findViewById(R.id.btnSelectDate);
        mTheDate = (TextView) findViewById(R.id.theDate);
        mTheTime = (TextView) findViewById(R.id.tvTime);
        mSelectTimeBtn = (Button) findViewById(R.id.btnSelectTime);

        //http://javarevisited.blogspot.com/2012/12/how-to-convert-millisecond-to-date-in-java-example.html
        mSelectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int min = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR);
                ////
                int theYear = c.get(Calendar.YEAR);
                int theMonth = c.get(Calendar.MONTH);
                int theDay = c.get(Calendar.DAY_OF_MONTH);

                TimePickerDialog dialog = new TimePickerDialog(
                        CreateEventActivity.this,
                        android.R.style.Theme_DeviceDefault_Dialog_MinWidth,
                        mTimeSetListener,
                        hour,min, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                DatePickerDialog dialogg = new DatePickerDialog(
                        CreateEventActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        theYear,theMonth,theDay);
                dialogg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogg.show();
//
//
//                java.util.Calendar timeTime = java.util.Calendar.getInstance();
//
//                timeTime.set(theYear, theMonth, theDay, hour, min);
//                long timeMillis = timeTime.getTimeInMillis();
//                Log.d(TAG, "onTimeSet:++++ " + timeMillis);
//
//                Date thisTime = new Date(timeMillis);
//
//                Log.d(TAG, "onClick: date +++++" + thisTime);
            }

        });

        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                mChosenDate.set(Calendar.HOUR_OF_DAY, hour);
                mChosenDate.set(Calendar.MINUTE, min);

// TODO: 3/2/18 make format - double digit ex. 02:04 not 2:4
                String timePicked = hour + ":" + min;
                mTheTime.setText(timePicked);

            }
        };

       mSelectTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int theYear = cal.get(Calendar.YEAR);
                int theMonth = cal.get(Calendar.MONTH);
                int theDay = cal.get(Calendar.DAY_OF_MONTH);
                //int theHour = cal.set(Calendar.HOUR_OF_DAY, hour);


                DatePickerDialog dialog = new DatePickerDialog(
                        CreateEventActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        theYear,theMonth,theDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int theYear, int theMonth, int theDay) {

                /////
                Calendar cal = Calendar.getInstance();
                cal.set(theYear, theMonth, theDay);
               // final long dateMillis = cal.getTimeInMillis();
               // Log.d(TAG, "onClick: dateMillis +++++ " + dateMillis);
                //Date thisDate = new Date(dateMillis);
                //Log.d(TAG, "onDateSet: ++ thisDate" + thisDate);
               // String dateeee = cal.getTime(dateMillis);

                //Log.d(TAG, "onDateSet: mm/dd/yyyy: " + theMonth + "/" + theDay + theYear);

                String datePicked = (theMonth + 1) + "/" + theDay + "/" + theYear;

                mTheDate.setText(datePicked);

                mChosenDate.set(Calendar.YEAR, theYear);
                mChosenDate.set(Calendar.MONTH, theMonth);
                mChosenDate.set(Calendar.DAY_OF_MONTH, theDay);
            }
        };

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSaveEventBtn = (Button) findViewById(R.id. btnSaveEvent);


        // Set the properties of the LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(CreateEventActivity.this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        // And now set it to the RecyclerView

        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);

        mRecyclerView.setAdapter(mAdapter);


//        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//        itemAnimator.setAddDuration(1000);
//        itemAnimator.setRemoveDuration(1000);
//        mRecyclerView.setItemAnimator(itemAnimator);

        mRecyclerView.setHasFixedSize(true);


        mSaveEventBtn.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEvent();
            }
        });

//
//        ////from Stack overflow https://stackoverflow.com/questions/41200876/how-to-set-onitemclicklistener-for-recyclerview
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



    }// end of onCreate()

    private void AddEvent() {//add event to firebase

        String name = mNameET.getText().toString().trim();
        String type = mSelectTypeSpn.getSelectedItem().toString();
        String date = mTheDate.getText().toString();


        String time = mTheTime.getText().toString();
        String address = mAddressET.getText().toString().trim();

        if(!TextUtils.isEmpty(name)) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference databaseEvents = FirebaseDatabase
                    .getInstance()
                    .getReference("events")
                    .child(uid);

            DatabaseReference pushRef = databaseEvents.push();
            String id = databaseEvents.push().getKey();
            long millis = mChosenDate.getTimeInMillis();
            Log.d(TAG, "AddEvent: current millis=" + millis + ", date=" + date + ", time=" + time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            Log.d(TAG, "AddEvent: parsed datetime=" + calendar.getTime().toString());

            Event event = new Event(id, name, type, date, time, millis, address);
            pushRef.setValue(event);

            Toast.makeText(this, "event added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(CreateEventActivity.this, "enter name of the event", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onStart() {
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

    private void showUpdateDialog(final String eventID, String eventName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText etName = (EditText) dialogView.findViewById(R.id.etNewName);
        final EditText etDate = (EditText) dialogView.findViewById(R.id.etNewDate);
        final EditText etTime = (EditText) dialogView.findViewById(R.id.etNewTime);
        final EditText etAddress = (EditText) dialogView.findViewById(R.id.etNewAddress);
        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Spinner spnUpdate = (Spinner) dialogView.findViewById(R.id.spnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Update Event " + eventName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = etName.getText().toString().trim();
//                String type = spnUpdate.getSelectedItem().toString();
//                String date = etDate.getText().toString().trim();
//                String time = etTime.getText().toString().trim();
//                String address = etAddress.getText().toString().trim();
//
//                if(TextUtils.isEmpty(name)){
//                    etName.setError("Description Required");
//                    return;
//                }
//                updateEvent(eventID, name, type, date, time, millis, address);
//
//                alertDialog.dismiss();
//            }
//        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteEvent(eventID);
                alertDialog.dismiss();
            }

        });
    }

    private void deleteEvent(String eventID) {
        DatabaseReference drEvent = FirebaseDatabase.getInstance().getReference("events").child(eventID);
        DatabaseReference drDetails = FirebaseDatabase.getInstance().getReference("details").child(eventID);

        drEvent.removeValue();
        drDetails.removeValue();

        Toast.makeText(this, "Event is deleted", Toast.LENGTH_LONG).show();
    }

    private boolean updateEvent(String id, String name, String type, String date, String time, long millis, String address){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events").child(id);

        Event event = new Event(id, name, type, date, time, millis, address);

        databaseReference.setValue(event);
        Toast.makeText(this, "Event updated successfully", Toast.LENGTH_LONG).show();

        return true;
    }


}
