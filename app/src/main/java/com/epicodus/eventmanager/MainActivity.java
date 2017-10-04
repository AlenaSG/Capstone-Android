package com.epicodus.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

# Capstone-Android/KidsAndMe
        by Alena Golovina, September 2017

        # DESCRIPTION
        This is a capstone project for the Epicodus Android Developer course.
        Event Manager for Kids and Moms.
        The user will be able to enter their events, sort them and add them to their calendar.

        ## Absolute minimum features the project requiresfor an MVP
        - Login the user in and out,
        - Saving events in database,
        - Sorting events by date and month,
        - Adding events to calendar

        ## Tools, frameworks, libraries, APIs, modules and/or other resources
        - Android Studio
        - Firebase Database

        ## Additional features
        - API calls for displaying daily quotes(Possible API for daily motivational quotes https://theysaidso.com/api/#qodcat)
        - Checking if there are meetups for the chosen event (Meetup API)
        - Setting reminders for coming events
        - Chat about a chosen event
        - Rating a past event
        - Advanced filtering (by distance, by age of kids. etc)


