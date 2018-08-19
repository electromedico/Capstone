package com.example.alex.capstone.utils;

import android.content.Context;

import com.example.alex.capstone.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInUtils {

    //Id Sign In
    public static final int RC_SIGN_IN = 100;

    /**
     * Method to get the GoogleSignInClient
     * @param context the activity
     * @return GoogleSignInClient
     */
    public static GoogleSignInClient getGoogleSignInClient(Context context){

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(context, gso);
    }
}
