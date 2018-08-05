package com.example.alex.capstone;

import android.os.Bundle;
import android.app.Activity;

public class BusStopInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_info);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
