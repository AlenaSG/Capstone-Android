package com.epicodus.eventmanager;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView mAppNameTextView;
    private Button mBtnCreateEvent;
    private Button mBtnFindEvent;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);//or setDrawerListener?
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //  actionbar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAppNameTextView = (TextView) findViewById(R.id.appNameTextView);
        mBtnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        mBtnFindEvent = (Button) findViewById(R.id.btnFindEvent);

//        //mBtnCreateEvent.setFocusable(true);
//        mBtnCreateEvent.setFocusableInTouchMode(true);///add this line
//        mBtnCreateEvent.requestFocus();



        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/satisfy.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                    // TODO: 1/31/18 fix Welcome, null message on login
                    // - it works on returning back to the activity, but not right after creating an account
                } else{

                }
            }

        };

        mBtnCreateEvent. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fill out the form and hit save!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });


        mBtnFindEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }// end of onCreate()

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);//is there another way to stay in the activity?
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here

        Fragment fragment = null;

        int id = item.getItemId();
        if (id == R.id.nav_account){
//            fragment = new MyAccountFragment();
//            //handle the account action
//            Log.d(TAG, "onNavigationItemSelected: this is account action");
            Intent intent = new Intent(MainActivity.this, WelcomeScreenActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are inside my account", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            Log.d(TAG, "onNavigationItemSelected: this is logout action");
            AlertDialog diaBox = AskOption();
            diaBox.show();
            //logout();
            return true;
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
            Log.d(TAG, "onNavigationItemSelected: this is settings action");
            Toast.makeText(this, "You are inside settings fragment", Toast.LENGTH_SHORT).show();
            mBtnCreateEvent.setEnabled(false);
            mBtnFindEvent.setEnabled(false);
        } else if (id == R.id.nav_quotes) {
            fragment = new QuoteFragment();
            Log.d(TAG, "onNavigationItemSelected: this is quotes action");
            //Toast.makeText(this, "You are inside settings fragment", Toast.LENGTH_SHORT).show();
            mBtnCreateEvent.setEnabled(false);
            mBtnFindEvent.setEnabled(false);
    }

        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            ft.replace(R.id.screen_area, fragment);
            //ft.commitNow();
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, SignInEmailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                //set message, title, and icon
                //.setTitle("Log Out")
                .setMessage("Do you want to log out?")
                //.setIcon(R.drawable.delete)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //my log out code/method
                        logout();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }

}

