package com.example.alex.capstone.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alex.capstone.R;
import com.example.alex.capstone.activities.login.LoginActivity;
import com.example.alex.capstone.activities.map.MapActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        //Test if the user already signed in or skipped the sign in
        if (account== null && !readFromPreferences()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
    }

    /**
     *Read from the Shared preference to check if the user skipped the log in
     * @return the value for not logged preference
     */
    private boolean readFromPreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getBoolean(getString(R.string.not_logged),false);
    }
}
