package com.epicodus.eventmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;
import android.graphics.Typeface;

import com.google.firebase.auth.FirebaseAuth;


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


    }
    // end of onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, SignInEmailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}

