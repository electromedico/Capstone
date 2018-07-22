package com.example.alex.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    //Id Sign In
    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "LOGIN";

    //UI
    @BindView(R.id.not_now_button)
    Button mNotNowButton;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        ButterKnife.bind(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Sing In button
        findViewById(R.id.g_plus_sign_in_button).setOnClickListener(new OnClickListener() {
            //Listener to launch the Sign in Activity
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mNotNowButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                skipSingIn();
            }
        });
    }

    /***
     *  Save the choice to shared preferences and then go to the Map Activity
     */
    private void skipSingIn() {

        writeToPreferencesSkipLogIn(true);
        startMapActivity();


    }

    /**
     * Launch the G+ sign in interface
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Message Log in Successful
                showSnackBar(getString(R.string.log_in_successful));
                //Write to preferences the choice
                writeToPreferencesSkipLogIn(false);


            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(TAG, getString(R.string.log_in_failed)+" code: " + e.getStatusCode());

                //Message Sign in Failed
               showSnackBar(getString(R.string.log_in_failed)+" "+ getString(R.string.please_try_again));

            }
            // in any case we Launch the Map activity
            startMapActivity();
        }
    }

    /**
     * Method to save the choice
     * @param b the choice made by the user
     */
    private void writeToPreferencesSkipLogIn(boolean b){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.not_logged), b);
        editor.apply();
    }

    /**
     * Start the Map Activity
     */
    private void startMapActivity(){
        Intent intent = new Intent(this,MapActivity.class);
        startActivity(intent);
    }

    private void showSnackBar(String msg){
        Snackbar  snackbar = Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG);
        snackbar.show();

    }

}

