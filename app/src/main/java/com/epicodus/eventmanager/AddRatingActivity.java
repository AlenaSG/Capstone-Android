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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//public class AddRatingActivity extends AppCompatActivity {
//
//    TextView tvEventName;
//    EditText etDetails;
//    SeekBar sbRating;
//    Button btnSaveRating;
//
//    ListView lvDetails;
//
//    DatabaseReference databaseRatings;
//
//    List<Detail> detailList;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_rating);
//
//        tvEventName = (TextView) findViewById(R.id.tvEventName);
//        etDetails = (EditText) findViewById(R.id.etDetails);
//        sbRating = (SeekBar) findViewById(R.id.sbRating);
//        btnSaveRating = (Button) findViewById(R.id.btnSaveRating);
//
//        lvDetails = (ListView) findViewById(R.id.lvDetails);
//
//        Intent intent = getIntent();
//
//        detailList = new ArrayList<>();
//
////        String id = intent.getStringExtra(CreateEventActivity.EVENT_ID);
////        String name = intent.getStringExtra(CreateEventActivity.EVENT_NAME);
//
//        //tvEventName.setText(name);
//
//        databaseRatings = FirebaseDatabase.getInstance().getReference("details").child(id);
//
//        btnSaveRating.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveDetails();
//
//            }
//        });
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        databaseRatings.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                detailList.clear();
//
//                for(DataSnapshot detailSnapshot : dataSnapshot.getChildren()){
//                    Detail detail = detailSnapshot.getValue(Detail.class);
//                    detailList.add(detail);
//                }
//
//                RatingList ratingListAdapter = new RatingList(AddRatingActivity.this, detailList);
//                lvDetails.setAdapter(ratingListAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void saveDetails() {
//        String detailText = etDetails.getText().toString().trim();
//        int rating = sbRating.getProgress();
//
//        if(!TextUtils.isEmpty(detailText)){
//            String id = databaseRatings.push().getKey();
//
//            Detail detail = new Detail(id, detailText, rating);
//
//            databaseRatings.child(id).setValue(detail);
//            Toast.makeText(this, "Details saved successfully", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Details should not be empty", Toast.LENGTH_LONG).show();
//        }
//
//    }
//}
