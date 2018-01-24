package com.epicodus.eventmanager;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventActivity extends AppCompatActivity {

    //define view objects
    private EditText mCreateEventET;
    private Button mSaveEventBtn;
    private Spinner mSelectTypeSpn;
    //private FirebaseDatabase database;

    DatabaseReference databaseEvents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        databaseEvents = FirebaseDatabase.getInstance().getReference("events");
        // Write a message to the database
        //database = FirebaseDatabase.getInstance();


        mCreateEventET = (EditText) findViewById(R.id. etCreateEvent);
        mSelectTypeSpn = (Spinner) findViewById(R.id.spnSelectType);

        mSaveEventBtn = (Button) findViewById(R.id. btnSaveEvent);
        mSaveEventBtn.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {

                AddEvent();
                //Toast.makeText(CreateEventActivity.this, "event is saved", Toast.LENGTH_LONG).show();
            }
        });

    }// end of onCreate()

    private void AddEvent() {
        String description = mCreateEventET.getText().toString().trim();
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
