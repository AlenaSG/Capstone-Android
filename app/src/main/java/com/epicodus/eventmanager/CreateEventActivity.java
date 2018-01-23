package com.epicodus.eventmanager;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventActivity extends AppCompatActivity {

    private EditText mCreateEventEditText;
    private Button mSaveEventButton;
    private FirebaseDatabase database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);


        // Write a message to the database
        database = FirebaseDatabase.getInstance();

        mCreateEventEditText = (EditText) findViewById(R.id. createEventEditText);


        mSaveEventButton = (Button) findViewById(R.id. saveEvent);
        mSaveEventButton.setOnClickListener(new View. OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = mCreateEventEditText.getText().toString();
                DatabaseReference myRef = database.getReference("event");

                myRef.setValue(description);

                Toast.makeText(CreateEventActivity.this, description + " saved", Toast.LENGTH_LONG).show();
            }
        });

    }// end of onCreate()
}
