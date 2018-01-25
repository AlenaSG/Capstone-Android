package com.epicodus.eventmanager;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    //define view objects
    private EditText mDescriptionET;
    private Button mSaveEventBtn;
    private Spinner mSelectTypeSpn;
    //private FirebaseDatabase database;

    ListView lvEvents;
    List<Event> eventList;
    DatabaseReference databaseEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        // Write a message to the database
        //database = FirebaseDatabase.getInstance();


        mDescriptionET = (EditText) findViewById(R.id. etDescription);
        mSelectTypeSpn = (Spinner) findViewById(R.id.spnSelectType);
        lvEvents = (ListView) findViewById(R.id.lvEvents);

        eventList = new ArrayList<>();

        mSaveEventBtn = (Button) findViewById(R.id. btnSaveEvent);
        mSaveEventBtn.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEvent();
                //Toast.makeText(CreateEventActivity.this, "event is saved", Toast.LENGTH_LONG).show();
            }
        });

    }// end of onCreate()

    @Override
    protected void onStart() {
        super.onStart();

        databaseEvents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventList.clear();

                for(DataSnapshot eventSnapshot : dataSnapshot.getChildren()){
                    Event event = eventSnapshot.getValue(Event.class);

                    eventList.add(event);
                }

                EventList adapter = new EventList(CreateEventActivity.this, eventList);
                lvEvents.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void AddEvent() {
        String description = mDescriptionET.getText().toString().trim();
        String type = mSelectTypeSpn.getSelectedItem().toString();

        if(!TextUtils.isEmpty(description)) {

            //will store it in database
            String id = databaseEvents.push().getKey();
            Event event = new Event(id, description, type);
            databaseEvents.child(id).setValue(event);

            Toast.makeText(this, "event added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(CreateEventActivity.this, "enter description", Toast.LENGTH_LONG).show();
        }
        //DatabaseReference myRef = database.getReference("event");

        //myRef.push().setValue(description);
    }
}
