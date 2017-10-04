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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/satisfy.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
    }
}

