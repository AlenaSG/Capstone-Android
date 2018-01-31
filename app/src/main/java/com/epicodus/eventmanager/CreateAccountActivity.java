package com.epicodus.eventmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class CreateAccountActivity extends AppCompatActivity {

    private Button mCreateUserButton;
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private TextView mLoginTextView;

    private static final String TAG = "CreateAccountActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mCreateUserButton = (Button) findViewById(R.id.createUserButton);
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        mConfirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
        mLoginTextView = (TextView) findViewById(R.id.loginTextView);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();


        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccountActivity.this, SignInEmailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        mCreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //see this method below
                createNewUser();
            }
        });
    }

    private void createNewUser() {
        final String name = mNameEditText.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);

        if (!validEmail || !validName || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Authentication successful");
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intentZ = new Intent(CreateAccountActivity.this, MainActivity.class);
                    startActivity(intentZ);
                    intentZ.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    finish();
                }
            }


        };
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

    private boolean isValidEmail(String email) {
        boolean isGoodEmail = (email !=null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmailEditText.setError("Please enter a valid e-mail address, eg. name@mail.com");
            return false;
        }
        return isGoodEmail;
    }

    private boolean isValidName(String name) {
        if(name.equals("")) {
            mNameEditText.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Please create a password containing at least 6 characters");
            return false;
        }else if(!password.equals(confirmPassword)){
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
