package com.epicodus.eventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

//import com.epicodus.eventmanager.Event;
//import com.epicodus.eventmanager.EventDetailFragment;

import java.util.ArrayList;

public class EventPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "EventPagerAdapter";
    private ArrayList<Event> mEvents;

    public EventPagerAdapter(FragmentManager fm, ArrayList<Event> events) {
        super(fm);
        mEvents = events;
    }

    @Override//returns an instance of EventDetailFragment - check this
    public Fragment getItem(int position) {
        return EventDetailFragment.newInstance(mEvents.get(position));
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mEvents.get(position).getName();
    }
}
