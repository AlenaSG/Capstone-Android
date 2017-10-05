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
    private Button mCreateEventButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        mCreateEventButton = (Button) findViewById(R.id.createEventButton);


        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/satisfy.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mCreateEventButton. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hi Mom!", Toast.LENGTH_LONG).show();
            }
        });
    }// end of onCreate()


}

