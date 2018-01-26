package com.epicodus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {

    public static final String EVENT_NAME = "eventname";
    public static final String EVENT_ID = "eventid";
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
            }
        });

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Event event = eventList.get(i);

                Intent intent = new Intent(getApplicationContext(), AddRatingActivity.class);

                intent.putExtra(EVENT_ID, event.getEventID());
                intent.putExtra(EVENT_NAME, event.getDescription());

                startActivity(intent);
            }
        });

       lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
               Event event = eventList.get(i);

               showUpdateDialog(event.getEventID(), event.getDescription());
               return false;
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

    private void showUpdateDialog(final String eventID, String eventDescription){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);

        dialogBuilder.setView(dialogView);

        final EditText etName = (EditText) dialogView.findViewById(R.id.etNewName);
        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Spinner spnUpdate = (Spinner) dialogView.findViewById(R.id.spnUpdate);

        dialogBuilder.setTitle("Update Event " + eventDescription);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = etName.getText().toString().trim();
                String type = spnUpdate.getSelectedItem().toString();

                if(TextUtils.isEmpty(description)){
                    etName.setError("Description Required");
                    return;
                }
                updateEvent(eventID, description, type);

                alertDialog.dismiss();
            }
        });


    }

    private boolean updateEvent(String id, String description, String type){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events").child(id);

        Event event = new Event(id, description, type);

        databaseReference.setValue(event);
        Toast.makeText(this, "Event updated successfully", Toast.LENGTH_LONG).show();

        return true;
    }

    private void AddEvent() {
        String description = mDescriptionET.getText().toString().trim();
        String type = mSelectTypeSpn.getSelectedItem().toString();

        if(!TextUtils.isEmpty(description)) {

            String id = databaseEvents.push().getKey();
            Event event = new Event(id, description, type);
            databaseEvents.child(id).setValue(event);

            Toast.makeText(this, "event added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(CreateEventActivity.this, "enter description", Toast.LENGTH_LONG).show();
        }

    }
}
