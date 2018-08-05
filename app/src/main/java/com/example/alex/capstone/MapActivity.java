package com.example.alex.capstone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.capstone.adapters.InfoWindowsRecyclerViewAdapter;
import com.example.alex.capstone.model.Departures;
import com.example.alex.capstone.model.Journey;
import com.example.alex.capstone.model.PhysicalStop;
import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.model.Place;
import com.example.alex.capstone.model.PlacesList;
import com.example.alex.capstone.model.StopArea;
import com.example.alex.capstone.networkUtils.GetCallController;
import com.example.alex.capstone.networkUtils.OnTaskCompleted;
import com.example.alex.capstone.utils.LatLongUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.alex.capstone.utils.LatLongUtils.RADIUS_METERS_OUTER_BOX;
import static com.example.alex.capstone.utils.LatLongUtils.RADIUS_METERS_ZOOM;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskCompleted {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation = null;
    private LatLng mlatLngPosition;
    private UiSettings mUiSettings;
    private List<PhysicalStop> mlistPhysicalStops;
    private CameraPosition cameraPosition;
    private Marker mClickMarker;
    private GetCallController getCallController;
    private LinearLayoutManager mLayoutManager;
    private String mSelectedStopAreaID;

    @BindView(R.id.favorites_image_button)
    ImageButton mFavoritesIb;
    @BindView(R.id.navigation_image_button)
    ImageButton mNavigationIb;
    @BindView(R.id.lines_image_view)
    ImageButton mLinesIb;
    @BindView(R.id.center_location_image_view)
    FloatingActionButton mCenterLocationFAB;
    @BindView(R.id.bottom_bar)
    View mBottomBarV;
    @BindView(R.id.info_window_view)
    View mInfoWindowV;
    private InfoWindowsRecyclerViewAdapter recyclerViewAdapter;
    @BindView(R.id.stop_name_tv)
    TextView mStopNameInfoWindowTv;
    @BindView(R.id.go_to_bus_stop_info_im)
    ImageButton mCloseInfoWindow;
    @BindView(R.id.search_view)
    android.support.v7.widget.SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //Instance of the Fused Location Provider Client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFavoritesIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapActivity.this,"Favorites", LENGTH_LONG).show();
            }
        });

        mNavigationIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mClickMarker==null){
                    Toast.makeText(MapActivity.this,"Choose a point in the map", LENGTH_LONG).show();
                }
                else {
                    //Todo implement navigation map activity
                    Toast.makeText(MapActivity.this,"Comming soon", LENGTH_LONG).show();
                }
            }
        });

        mLinesIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapActivity.this,"Lines", LENGTH_LONG).show();
            }
        });
        mCenterLocationFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(mlatLngPosition));
            }
        });

        mCloseInfoWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomBarV.setVisibility(View.VISIBLE);
                mInfoWindowV.setVisibility(View.GONE);
            }
        });

        getCallController = new GetCallController(this, this);

        //InfoWindow Recycler view SetUp
        RecyclerView mRecyclerView = findViewById(R.id.bus_schedules_info_window_rv);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

        recyclerViewAdapter = new InfoWindowsRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(recyclerViewAdapter);

        //SearchView Configuration
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length()>3){
                    getCallController.startGetStopSchedules(newText);
                }
                return true;
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int mapsPadding =  getResources().getInteger(R.integer.maps_padding);
        mGoogleMap = googleMap;
        if(cameraPosition!=null)mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mGoogleMap.setPadding(0,mapsPadding,0,mapsPadding);
        mUiSettings = mGoogleMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(false);
        mUiSettings.setMapToolbarEnabled(false);

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTag() instanceof PhysicalStop){
                    if (mClickMarker!=null)mClickMarker.remove();
                    PhysicalStop physicalStop = (PhysicalStop) marker.getTag();
                    mSelectedStopAreaID=physicalStop.getId();
                    //We call the service to get the Schedules
                    getCallController.startGetStopSchedules(physicalStop.getId());

                }
                return false;
            }
        });

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mClickMarker!=null)mClickMarker.remove();
                mBottomBarV.setVisibility(View.VISIBLE);
                mInfoWindowV.setVisibility(View.GONE);
                mClickMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                );

            }
        });

        getLastLocation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.camera_key),mGoogleMap.getCameraPosition());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey(getString(R.string.camera_key))){
            cameraPosition = savedInstanceState.getParcelable(getString(R.string.camera_key));
        }
    }

    @Override
    public void onTaskCompletedGetNearbyStops(PhysicalStops physicalStops) {
        loadInfoToMap(physicalStops);
    }

    @Override
    public void onTaskCompletedGetStopSchedules(Departures departures) {
        if (departures!=null && !departures.getStopAreas().isEmpty())
        {   List<StopArea> stopAreas = departures.getStopAreas();
            for (StopArea stopArea : stopAreas){
                if (stopArea!=null && stopArea.getUniqueStopId().equals(mSelectedStopAreaID)){
                    showInfoWindow(stopArea);
                }
            }
        }
    }

    @Override
    public void onTaskCompletedGetJourneys(Journey journey) {

    }

    @Override
    public void onTaskCompletedGetPlaces(PlacesList placesList) {
        if (placesList!=null && !placesList.getPlace().isEmpty()){
            for (Place place : placesList.getPlace()){
            //     searchView.setSuggestionsAdapter();
            }


        }
        else{

        }

    }


    private void getLastLocation() {

        //check for permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission_group.LOCATION)) {

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("this is needed")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
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
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            Task<Location> locationTask = mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @SuppressLint("MissingPermission")
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                mLocation = location;
                                mlatLngPosition = locationToLatLong(mLocation);

                                //call the service to get all the stop zones
                                LatLngBounds latLngBoundsOuterBox = LatLongUtils.calculateBoundingBox(RADIUS_METERS_OUTER_BOX, mlatLngPosition);
                                getCallController.startGetNearbyStops(latLngBoundsOuterBox);

                                //Bounding box to calculate the zoom with RADIUS_METERS_ZOOM and move the camera to last known location
                                LatLngBounds latLngBounds = LatLongUtils.calculateBoundingBox(RADIUS_METERS_ZOOM, mlatLngPosition);
                                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
                                mGoogleMap.setMyLocationEnabled(true);
                            }

                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_LOCATION:
                getLastLocation();
        }
    }

    private void loadInfoToMap(PhysicalStops physicalStops){
        if (physicalStops != null && !physicalStops.getPhysicalStop().isEmpty()) {
            mlistPhysicalStops = physicalStops.getPhysicalStop();
            for (PhysicalStop physicalStop : mlistPhysicalStops) {

                //The API has inverted the lat and long values
                Double y = Double.valueOf(physicalStop.getX());
                Double x = Double.valueOf(physicalStop.getY());
                LatLng latLng = new LatLng(x, y);
                Drawable drawable = getDrawable(R.drawable.ic_marker);
                //Vector Drawable to Bitmap
                Bitmap icon = com.example.alex.capstone.utils.ResourceUtils.getBitmap((VectorDrawable) drawable);

                Marker marker = mGoogleMap.addMarker(new MarkerOptions().
                        position(latLng)
                        .icon(BitmapDescriptorFactory.fromBitmap(icon)));
                marker.setTag(physicalStop);
            }
        } else {
            Toast.makeText(this, getString(R.string.no_stops), LENGTH_LONG).show();
        }

    }

    /***
     * Method to load the information in the Info Window
     * @param stopArea
     */
    private void showInfoWindow(StopArea stopArea) {
        mBottomBarV.setVisibility(View.GONE);
        mInfoWindowV.setVisibility(View.VISIBLE);
        mStopNameInfoWindowTv.setText(stopArea.getName());
        recyclerViewAdapter.setmDataSet(stopArea);

    }

    private LatLng locationToLatLong (Location location){
        Double y = location.getLongitude();
        Double x = location.getLatitude();
        LatLng latLng = new LatLng(x, y);
        return latLng;
    }

}
