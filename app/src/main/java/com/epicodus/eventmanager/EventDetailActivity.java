package com.epicodus.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

public class EventDetailActivity extends AppCompatActivity {
    ViewPager mViewPager;

    EventPagerAdapter adapterViewPager;
    ArrayList<Event> mEvents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mEvents = Parcels.unwrap(getIntent().getParcelableExtra("events"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        //int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

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
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_update) {
            //logout();
            Toast.makeText(this, "LETS WRITE SOME CODE FOR UPDATE", Toast.LENGTH_SHORT).show();
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
}
