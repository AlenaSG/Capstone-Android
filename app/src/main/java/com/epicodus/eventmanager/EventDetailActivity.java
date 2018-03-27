package com.epicodus.eventmanager;

import android.support.v4.view.PagerTabStrip;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {
    ViewPager mViewPager;
    //PagerTabStrip mPagerTabStrip;

    EventPagerAdapter adapterViewPager;
    ArrayList<Event> mEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        //mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagerHeader);

        mEvents = Parcels.unwrap(getIntent().getParcelableExtra("events"));
        //int startingPosition = getIntent().getIntExtra("position", 0);
        //String eventName = getIntent().getStringExtra("eventName");
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        //String idishka = mPagerTabStrip.getText().toString().trim();

        //intent.putExtra("position", itemPosition + "");
        adapterViewPager = new EventPagerAdapter(getSupportFragmentManager(), mEvents);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_update) {
            //showUpdateDialog(eventName);
            Toast.makeText(this, "Write some update code", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    //Here, we create a new Intent to return the user to the LoginActivity, add flags to remove the current activity
    // from our stack, start the new Intent, and end the current instance of MainActivity with the finish() method.
//    private void logout() {
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        finish();
//    }

    /////////////////////////////////////////////////////////////////////////////////
    private void showUpdateDialog(final String eventID){
        //private void showUpdateDialog(final String eventID, String eventName){
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

        final EditText etMillis = (EditText) dialogView.findViewById(R.id.etNewMillis);


        //dialogBuilder.setTitle("Update Event " + eventName);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String type = spnUpdate.getSelectedItem().toString();
                String date = etDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();
                String textMillis = etMillis.getText().toString().trim();
                long millis = Integer.parseInt(textMillis);//converting string value to integer-long
                String address = etAddress.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    etName.setError("Description Required");
                    return;
                }
                updateEvent(eventID, name, type, date, time, millis, address);
                //updateEvent(eventID, name, type, date, time, millis, address);

                alertDialog.dismiss();
            }
        });

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
