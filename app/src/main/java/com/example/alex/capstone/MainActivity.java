package com.example.alex.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);

        if (account== null || !readFromPreferences()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }
    }

    public boolean readFromPreferences(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPref.getBoolean(getString(R.string.not_loged),false);
    }
}
