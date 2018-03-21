package com.epicodus.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
}
