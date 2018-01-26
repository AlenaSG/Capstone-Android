package com.epicodus.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRaitingActivity extends AppCompatActivity {

    TextView tvEventName;
    EditText etDetails;
    SeekBar sbRaiting;
    Button btnSaveRaiting;

    ListView lvDetails;

    DatabaseReference databaseRaitings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_raiting);

        tvEventName = (TextView) findViewById(R.id.tvEventName);
        etDetails = (EditText) findViewById(R.id.etDetails);
        sbRaiting = (SeekBar) findViewById(R.id.sbRaiting);
        btnSaveRaiting = (Button) findViewById(R.id.btnSaveRaiting);

        lvDetails = (ListView) findViewById(R.id.lvDetails);

        Intent intent = getIntent();

        String id = intent.getStringExtra(CreateEventActivity.EVENT_ID);
        String name = intent.getStringExtra(CreateEventActivity.EVENT_NAME);

        tvEventName.setText(name);

        databaseRaitings = FirebaseDatabase.getInstance().getReference("details").child(id);

        btnSaveRaiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();

            }
        });

    }

    private void saveDetails() {
        String detailText = etDetails.getText().toString().trim();
        int rating = sbRaiting.getProgress();

        if(!TextUtils.isEmpty(detailText)){
            String id = databaseRaitings.push().getKey();

            Detail detail = new Detail(id, detailText, rating);

            databaseRaitings.child(id).setValue(detail);
            Toast.makeText(this, "details saved successfully", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Details should not be empty", Toast.LENGTH_LONG).show();
        }

    }
}
