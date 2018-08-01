package com.example.alex.capstone;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.networkUtils.GetCallController;
import com.example.alex.capstone.networkUtils.OnTaskCompleted;
import com.example.alex.capstone.utils.LatLongUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskCompleted {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100 ;
    private GoogleMap googleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation=null;
    private LatLngBounds latLngBounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Instance of the Fused Location Provider Client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getLastLocation();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        /*
         Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

    }

    @Override
    public void onTaskCompletedGetNearbyStops(PhysicalStops physicalStops) {
        if (physicalStops != null) {
            //The API has inverted the lat and long values
            Double y = Double.valueOf(physicalStops.getPhysicalStop().get(0).x);
            Double x = Double.valueOf(physicalStops.getPhysicalStop().get(0).y);
            LatLng l1 = new LatLng(x, y);
            Drawable drawable = getDrawable(R.drawable.ic_marker);
            //Vector Drawable to Bitmap
            Bitmap icon = com.example.alex.capstone.utils.ResourceUtils.getBitmap((VectorDrawable) drawable);

            Marker marker = googleMap.addMarker(new MarkerOptions().
                    position(l1).
                    icon(BitmapDescriptorFactory.fromBitmap(icon)));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(mLocation.getLatitude(),mLocation.getLongitude())));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,0));

        }
    }

    private void getLastLocation() {

        //check for permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission_group.LOCATION)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("this is needed")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create()
                        .show();
            }
            else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
        else {
            Task<Location> locationTask = mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                mLocation = location;
                                loadInfoToMap(mLocation);
                            }

                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_LOCATION:
               //getLastLocation();
        }
    }

    private void loadInfoToMap(Location location){
        Double y = location.getLongitude();
        Double x = location.getLatitude();
        LatLng latLng = new LatLng(x, y);
        latLngBounds = LatLongUtils.calculateBondingBox(latLng);
        GetCallController getCallController = new GetCallController(this, this);
        getCallController.startGetNearbyStops(latLngBounds);

    }

}
