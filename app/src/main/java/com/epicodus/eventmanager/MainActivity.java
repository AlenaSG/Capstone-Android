package com.epicodus.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;
import android.graphics.Typeface;



public class MainActivity extends AppCompatActivity {

    private TextView mAppNameTextView;
    private Button mBtnCreateEvent;
    private Button mBtnFindEvent;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        mBtnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        mBtnFindEvent = (Button) findViewById(R.id.btnFindEvent);



        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/satisfy.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mBtnCreateEvent. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fill out the form and hit save!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        mBtnFindEvent. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Find your event in calendar", Toast.LENGTH_LONG).show();
                Intent intentF = new Intent(MainActivity.this, FindEventActivity.class);
                startActivity(intentF);
            }
        });
    }// end of onCreate()


}

