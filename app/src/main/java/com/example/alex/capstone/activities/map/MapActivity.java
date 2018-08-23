package com.example.alex.capstone.activities.map;

import android.Manifest;

import android.app.ActivityOptions;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.capstone.activities.about.AboutActivity;
import com.example.alex.capstone.activities.favorites.FavoritesActivity;
import com.example.alex.capstone.R;
import com.example.alex.capstone.activities.map.adapters.recyclerview.InfoWindowsRecyclerViewAdapter;
import com.example.alex.capstone.activities.map.adapters.searchview.SearchSuggestionsAdapter;
import com.example.alex.capstone.data.FavoriteEntry;
import com.example.alex.capstone.data.FavoritesContract;
import com.example.alex.capstone.data.dataUtils.DbReadByIDAsyncTask;
import com.example.alex.capstone.data.dataUtils.DbReadByLatLongNameAsyncTask;
import com.example.alex.capstone.model.Departures;
import com.example.alex.capstone.model.PhysicalStop;
import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.model.Place;
import com.example.alex.capstone.model.PlacesList;
import com.example.alex.capstone.model.StopArea;
import com.example.alex.capstone.model.getJourneysQueryModel.Chunk;
import com.example.alex.capstone.model.getJourneysQueryModel.Service;
import com.example.alex.capstone.model.getJourneysQueryModel.Stop;
import com.example.alex.capstone.model.getJourneysQueryModel.Street;
import com.example.alex.capstone.networkutils.GetCallController;
import com.example.alex.capstone.networkutils.OnTaskCompleted;
import com.example.alex.capstone.networkutils.callControlers.GetNearbyStopsController;
import com.example.alex.capstone.utils.GoogleSignInUtils;
import com.example.alex.capstone.utils.LocationUtils;
import com.example.alex.capstone.utils.MapUtils;
import com.example.alex.capstone.widget.UpdateWidgetService;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.alex.capstone.activities.favorites.FavoritesActivity.FAVORITES_ACTIVITY_TAG;
import static com.example.alex.capstone.activities.map.adapters.searchview.SearchSuggestionsAdapter.SUGGESTION_CURSOR_FIELDS;
import static com.example.alex.capstone.data.dataUtils.DbUtils.addFavoriteQuery;
import static com.example.alex.capstone.data.dataUtils.DbUtils.deleteFavoriteQuery;
import static com.example.alex.capstone.utils.DataUtils.cursorToEntryList;
import static com.example.alex.capstone.utils.DataUtils.testSingleFavoriteArraySize;
import static com.example.alex.capstone.utils.GoogleSignInUtils.RC_SIGN_IN;
import static com.example.alex.capstone.utils.GoogleSignInUtils.getGoogleSignInClient;
import static com.example.alex.capstone.utils.MapUtils.RADIUS_METERS_ZOOM;
import static com.example.alex.capstone.utils.MapUtils.calculateBoundingBox;
import static com.example.alex.capstone.widget.FavoritesWidgetProvider.WIDGET_ID_KEY;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskCompleted, GoogleMap.OnCameraIdleListener, NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    //MapActivity TAG
    private static final String MAP_ACTIVITY_TAG=MapActivity.class.getSimpleName();
    //Constant for PERMISSIONS_REQUEST
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;

    // Constants for logging and referring to a unique loader
    private static final int FAVORITE_READ_BY_ID_LOADER = 2;
    private static final int FAVORITE_READ_BY_LAT_LNG_LOADER = 3;

    //Constants for Args in Loader
    public static final String LAT_TAG = "LAT";
    public static final String LNG_TAG = "LNG";
    private static final String ID_TAG = "ID";
    private static final String LOG_IN_MENU_TAG = "LOG_IN_MENU";


    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation = null;
    private LatLng mLatLngPosition;
    private List<PhysicalStop> mListPhysicalStops;
    private CameraPosition cameraPosition;
    private Marker mClickMarker;
    private GetCallController getCallController;
    private String mSelectedStopAreaID;
    private List<Place> placeList;
    private List<Marker> mStopMarkers = new ArrayList<>();
    private List<Polyline> mPolyLines = new ArrayList<>();
    private HashMap<String, Object> mMarkerInfoMap = new HashMap<>();
    private boolean mIsFavorite = false;
    private boolean mCalledByFavorites = false;
    private boolean mIsUserLogged=false;
    private PhysicalStop mPhysicalStop;
    private long mFavoriteId = -1;
    private FavoriteEntry favoriteEntry;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private InfoWindowsRecyclerViewAdapter recyclerViewAdapter;
    private GoogleSignInClient mGoogleSignInClient;
    private StopArea mStopArea;


    @BindView(R.id.favorites_image_button)
    ImageButton mFavoritesIb;
    @BindView(R.id.navigation_image_button)
    ImageButton mNavigationIb;
    @BindView(R.id.center_location_fab)
    FloatingActionButton mCenterLocationFAB;
    @BindView(R.id.bottom_bar)
    View mBottomBarV;
    @BindView(R.id.info_window_view)
    View mInfoWindowV;
    @BindView(R.id.stop_name_tv)
    TextView mStopNameInfoWindowTv;
    @BindView(R.id.close_bus_stop_info_im)
    ImageButton mCloseInfoWindow;
    @BindView(R.id.search_view)
    android.support.v7.widget.SearchView searchView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view_menu)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_icon_ib)
    ImageButton mDrawerToggleIb;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.go_to_bus_stop_info_bt)
    Button mGotoBusStopBt;
    @BindView(R.id.center_add_fab)
    FloatingActionButton mCenterAddFavoritesFAB;

    TextView mNavUserNameTv;
    Menu mMenu;
    MenuItem mMenuItemLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        //get GoogleSignInClient
        mGoogleSignInClient=getGoogleSignInClient(this);

        //Get last signed account to manage the nav header
        View view = mNavigationView.getHeaderView(0);
        mMenu=mNavigationView.getMenu();
        mNavUserNameTv=view.findViewById(R.id.nav_user_nav_tv);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //If tje user is connected we manage the menu item
        if (account!= null){
            mIsUserLogged=true;
            String userName = account.getGivenName();
            String navUserText= getString(R.string.nav_header_subtitle_hi).concat(userName);
            mNavUserNameTv.setText(navUserText);
            mMenuItemLog = mMenu.findItem(R.id.log);
            mMenuItemLog.setTitle(R.string.log_out_menu_title);
            mMenuItemLog.setIcon(getResources().getDrawable(R.drawable.ic_person_outline_black_24dp,null));

        }

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //SetUp the location Services
        //Instance of the Fused Location Provider Client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //get user location Callback
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                mLocation = locationResult.getLastLocation();
                mLatLngPosition = LocationUtils.locationToLatLong(mLocation);
            }

        };
        //Set up the location request
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);


        ///check for who launched the activity
        Bundle intentExtras = getIntent().getExtras();
        if (intentExtras != null) {
           Gson gson = new Gson();
            if (intentExtras.containsKey(WIDGET_ID_KEY)) {
                mCalledByFavorites = true;
                favoriteEntry = gson.fromJson(intentExtras.getString(getString(R.string.favorite_json_key)), FavoriteEntry.class);
            }
            else if(intentExtras.containsKey(FAVORITES_ACTIVITY_TAG)){
                mCalledByFavorites = true;
                favoriteEntry = gson.fromJson(intentExtras.getString(FAVORITES_ACTIVITY_TAG), FavoriteEntry.class);
            }
        }

        getCallController = new GetCallController(this, this);

        //InfoWindow Recycler view SetUp
        RecyclerView mRecyclerView = findViewById(R.id.bus_schedules_info_window_rv);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter
        recyclerViewAdapter = new InfoWindowsRecyclerViewAdapter();
        mRecyclerView.setAdapter(recyclerViewAdapter);

        mNavigationView.setNavigationItemSelectedListener(this);

        //Setup the buttons onClickListener
        setUpButtons();
        //SearchView Configuration
        setUpSearchView();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (searchView != null) {
            searchView.setQuery("", false);
            searchView.setFocusable(false);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
        //true if onRestoreInstanceState contains mPhysicalStop
        if (mPhysicalStop!=null){
            showInfoWindow(mStopArea);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.camera_key), mGoogleMap.getCameraPosition());
        outState.putParcelable(getString(R.string.physical_stop_key),mPhysicalStop);
        outState.putParcelable(getString(R.string.stop_area_key),mStopArea);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState==null) return;

        if (savedInstanceState.containsKey(getString(R.string.camera_key))) {
            cameraPosition = savedInstanceState.getParcelable(getString(R.string.camera_key));
        }
        if (savedInstanceState.containsKey(getString(R.string.physical_stop_key))) {
            mPhysicalStop = savedInstanceState.getParcelable(getString(R.string.physical_stop_key));
            if (mPhysicalStop!= null)mSelectedStopAreaID=mPhysicalStop.getId();

        }
        if (savedInstanceState.containsKey(getString(R.string.stop_area_key))) {
            mStopArea = savedInstanceState.getParcelable(getString(R.string.stop_area_key));

        }
    }

    /**
     * start location Updates
     */
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Method the manages the location permission
            askLocationServicesPermission();

        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
        }
    }

    /**
     * Stop the location updates to reduce power consumption
     */
    private void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    /**
     * onCreate method to setup the buttons click listeners
     */
    private void setUpButtons() {
        mFavoritesIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFavorites();
            }
        });

        mNavigationIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mClickMarker == null) {
                    Toast.makeText(MapActivity.this, getString(R.string.chose_a_point_in_the_map), LENGTH_LONG).show();
                } else {
                    callGetJourney(mClickMarker);
                }
            }
        });

        mCenterLocationFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLatLngPosition != null) {
                    moveCamera(mLatLngPosition);
                }

            }
        });

        mCloseInfoWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               hideInfoWindow();
            }
        });

        mDrawerToggleIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        mCenterAddFavoritesFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageFavorites();

            }
        });

        mGotoBusStopBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickMarker == null) {
                    Log.e(MAP_ACTIVITY_TAG,getString(R.string.on_click_go_to_bus_stop) +
                            getString(R.string.mclickmarker_null));
                    Toast.makeText(MapActivity.this,getString(R.string.something_went_wrong),LENGTH_LONG).show();
                } else {
                    callGetJourney(mClickMarker);
                    hideInfoWindow();
                }
            }
        });

    }

    /**
     * On create method to setup the SearchView
     */
    private void setUpSearchView() {
        //Set the suggestions adapter
        searchView.setSuggestionsAdapter(
                new SearchSuggestionsAdapter(this,
                        R.layout.suggestions_item_layout,
                        new MatrixCursor(SUGGESTION_CURSOR_FIELDS),
                        new int[]{R.id.suggestion_tv}));

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 2) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    getCallController.startGetPlaces(newText);
                } else {
                    //cleat the suggestions list
                    searchView.getSuggestionsAdapter().swapCursor(new MatrixCursor(SUGGESTION_CURSOR_FIELDS));
                }
                return true;
            }
        });

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Place place = placeList.get(position);
                searchView.setQuery(place.getLabel(), true);
                if (mClickMarker != null) mClickMarker.remove();
                mClickMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.valueOf(place.getY()), Double.valueOf(place.getX())))
                );
                LatLngBounds latLngBounds = MapUtils.calculateBoundingBox(RADIUS_METERS_ZOOM, mClickMarker.getPosition());
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));

                return true;
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final int mapsPadding = getResources().getInteger(R.integer.maps_padding);
        mGoogleMap = googleMap;
        if (cameraPosition != null)
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mGoogleMap.setPadding(0, mapsPadding, 0, mapsPadding);
        mGoogleMap.setOnCameraIdleListener(this);

        UiSettings mUiSettings = mGoogleMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(false);
        mUiSettings.setMapToolbarEnabled(false);

        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {

                if (marker.getTag() instanceof PhysicalStop) {
                    mProgressBar.setVisibility(View.VISIBLE);
                    if (mClickMarker != null) mClickMarker.remove();
                    mClickMarker = marker;
                    //we are having problems storing the object in the Marker.setTAg, we use instead a Map
                    mMarkerInfoMap.put(marker.getId(), marker.getTag());
                    mPhysicalStop = (PhysicalStop) marker.getTag();
                    mSelectedStopAreaID = mPhysicalStop.getId();
                    //We call the service to get the Schedules
                    getCallController.startGetStopSchedules(mPhysicalStop.getId());
                    // we call the AsyncTask to query the favorites DB
                    Bundle args = new Bundle();
                    args.putString(LAT_TAG, String.valueOf(marker.getPosition().latitude));
                    args.putString(LNG_TAG, String.valueOf(marker.getPosition().longitude));
                    queryAllByLatLngFavoritesDB(args);

                }
                return false;
            }
        });

        mGoogleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (mClickMarker != null) mClickMarker.remove();
                hideInfoWindow();
                mClickMarker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                );

            }
        });

        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mClickMarker != null) mClickMarker.remove();
                hideInfoWindow();

            }
        });

        //Enable user location Marker
        enableGoogleMapMyLocation();

        //If the activity was called by the widget we focus the favorite location instead
        if (cameraPosition!=null){
            mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        else if (mCalledByFavorites) {
            moveCamera(favoriteEntry.getLatLng());
            //Only used for on create
            mCalledByFavorites = false;
        } else {
            if (!moveCamera(mLatLngPosition))getLastLocation();

        }
    }

    private void enableGoogleMapMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            askLocationServicesPermission();
        } else {
            //User Location enabled
            if (mGoogleMap!=null){
                mGoogleMap.setMyLocationEnabled(true);
            }

        }
    }



    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           askLocationServicesPermission();
            return;
        }
        Task<Location> lastLocation = mFusedLocationClient.getLastLocation();
        lastLocation.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                mLocation=location;
                mLatLngPosition=LocationUtils.locationToLatLong(mLocation);
                moveCamera(mLatLngPosition);
            }
        });
    }


    @Override
    public void onTaskCompletedGetNearbyStops(PhysicalStops physicalStops) {
        mProgressBar.setVisibility(View.GONE);
        loadInfoToMap(physicalStops);
    }

    @Override
    public void onTaskCompletedGetStopSchedules(Departures departures) {
        mProgressBar.setVisibility(View.GONE);
        if (departures != null && !departures.getStopAreas().isEmpty()) {
            List<StopArea> stopAreas = departures.getStopAreas();
            for (StopArea stopArea : stopAreas) {
                if (stopArea != null && stopArea.getUniqueStopId().equals(mSelectedStopAreaID)) {
                    showInfoWindow(stopArea);
                    mStopArea=stopArea;
                }
            }
        }
    }

    @Override
    public void onTaskCompletedGetJourneys(List<com.example.alex.capstone.model.getJourneysQueryModel.Journey> journeys) {
        mProgressBar.setVisibility(View.GONE);
        loadJourneyIntoMap(journeys);
    }

    @Override
    public void onTaskCompletedGetPlaces(PlacesList placesList) {
        mProgressBar.setVisibility(View.GONE);
        if (placesList != null && !placesList.getPlace().isEmpty()) {

            //we fill the list
            placeList = placesList.getPlace();
            //MatrixCursor SetUp
            MatrixCursor suggestionsCursor = new MatrixCursor(SUGGESTION_CURSOR_FIELDS);

            //we loop the placeList
            for (int i = 0; i < placeList.size(); i++) {
                String placeLabel = placeList.get(i).getLabel();
                suggestionsCursor.addRow(new String[]{String.valueOf(i), placeLabel});
            }
            searchView.getSuggestionsAdapter().swapCursor(suggestionsCursor);
        } else {

        }

    }

    private void askLocationServicesPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission_group.LOCATION)) {

            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.permission_is_needed))
                    .setMessage(getString(R.string.why_permission_is_needed))
                    .setPositiveButton(getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MapActivity.this,
                                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION);
                        }
                    })
                    .setNegativeButton(getString(R.string.cancel_button), new DialogInterface.OnClickListener() {
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                startLocationUpdates();
                enableGoogleMapMyLocation();
                getLastLocation();
        }
    }

    /**
     * This method load all draws the polyline with the information from the API
     * @param journeys The journey proposed by the API
     */
    private void loadJourneyIntoMap(List<com.example.alex.capstone.model.getJourneysQueryModel.Journey> journeys) {
        clearPolyline();

        if (journeys!= null && !journeys.isEmpty() && journeys.get(0).getJourney()!=null){
            List<Chunk> chunks = journeys.get(0).getJourney().getChunks();
            try {
                //Chunk cannot be empty
                if (chunks.isEmpty()){
                    throw new IOException("Chunks is empty");
                }else {
                    for (Chunk chunk : chunks){
                        //Chunk can contain three types of objects we load the information accordingly
                        if (chunk.getService()!= null) loadServiceInfoIntoMap(chunk.getService());
                        if (chunk.getStop()!= null) loadStopInfoIntoMap(chunk.getStop());
                        if (chunk.getStreet()!=null) loadStreetInfoIntoMAp(chunk.getStreet());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(MAP_ACTIVITY_TAG,getString(R.string.load_journey)
                        +getString(R.string.chunks_is_empty));
            }
        }
        else {
            Toast.makeText(this,getString(R.string.no_journeys_found),LENGTH_LONG).show();
        }
    }

    private void loadStreetInfoIntoMAp(Street street) {
        String wtk =street.getWkt();
        try {
            List<List<LatLng>> latLngsList=MapUtils.wtkMultiLineStringToLatLngList(this,wtk);
            for (List<LatLng> line : latLngsList){
                LatLng[] latLngs = line.toArray(new LatLng[0]);
                List<PatternItem> pattern = Arrays.asList(new Dash(30),new Gap(30));
                Polyline polyline = mGoogleMap.addPolyline(new PolylineOptions()
                        .clickable(false)
                        .add(latLngs)
                        .color(Color.BLACK)
                        .pattern(pattern)
                );
                mPolyLines.add(polyline);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * No yet implemented
     * @param stop
     */
    private void loadStopInfoIntoMap(Stop stop) {
    }

    private void loadServiceInfoIntoMap(Service service) {
        String wtk = service.getWkt();
        try {
            List<LatLng> latLngList = MapUtils.wtkLineStringToLatLngList(this, wtk);
            LatLng[]latLngs= latLngList.toArray(new LatLng[0]);
            Polyline polyline = mGoogleMap.addPolyline(new PolylineOptions()
                    .clickable(false)
                    .add(latLngs));
            mPolyLines.add(polyline);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadInfoToMap(PhysicalStops physicalStops){
        if (physicalStops != null && !physicalStops.getPhysicalStop().isEmpty()) {
            mListPhysicalStops = physicalStops.getPhysicalStop();
            for (PhysicalStop physicalStop : mListPhysicalStops) {

                //The API has inverted the lat and long values
                Double x = Double.valueOf(physicalStop.getX());
                Double y = Double.valueOf(physicalStop.getY());
                LatLng latLng = new LatLng(y,x);
                Drawable drawable = getDrawable(R.drawable.ic_marker);
                //Vector Drawable to Bitmap
                Bitmap icon = com.example.alex.capstone.utils.ResourceUtils.getBitmap((VectorDrawable) drawable);
                Marker marker = mGoogleMap.addMarker(new MarkerOptions().
                        position(latLng)
                        .icon(BitmapDescriptorFactory.fromBitmap(icon)));
                marker.setTag(physicalStop);
                mStopMarkers.add(marker);
                //Only true if onRestoreInstanceState contains mPhysicalStop
                if (mPhysicalStop!=null &&mPhysicalStop.getId().equals(physicalStop.getId())){
                    mClickMarker=marker;
                }
            }
        } else {
            Toast.makeText(this, getString(R.string.no_stops), LENGTH_LONG).show();
        }

    }

    private boolean moveCamera(LatLng latLng){
        /* In some cases the map view has not being loaded and there is an exception.
        to avoid this we pass the dimensions of the map screen*/
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

        if (latLng!=null){
            LatLngBounds latLngBounds = calculateBoundingBox(MapUtils.RADIUS_METERS_ZOOM, latLng);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,width,height,padding));
            return true;

        }
        else {
            return false;
        }

    }

    /***
     * Method to load the information in the Info Window
     * @param stopArea StopAre to show
     */
    private void showInfoWindow(StopArea stopArea) {
        mBottomBarV.setVisibility(View.GONE);
        mCenterAddFavoritesFAB.setVisibility(View.VISIBLE);
        mInfoWindowV.setVisibility(View.VISIBLE);
        mStopNameInfoWindowTv.setText(stopArea.getName());
        recyclerViewAdapter.setmDataSet(stopArea);
    }

    /**
     *Method to hide the info window
     */
    private void hideInfoWindow(){
        mClickMarker=null;
        mPhysicalStop=null;
        mBottomBarV.setVisibility(View.VISIBLE);
        mInfoWindowV.setVisibility(View.GONE);
        mCenterAddFavoritesFAB.setVisibility(View.GONE);
    }


    @Override
    public void onCameraIdle() {

        clearStopMarkers();
        float zoomValue = Float.parseFloat(getString(R.string.map_zoom_level));
        if (mGoogleMap.getCameraPosition().zoom >zoomValue){
            mProgressBar.setVisibility(View.VISIBLE);
            //call the service to get all the stop zones
            cameraPosition=mGoogleMap.getCameraPosition();
            LatLngBounds latLngBoundsOuterBox=mGoogleMap.getProjection().getVisibleRegion().latLngBounds;
            new GetNearbyStopsController(this,this).startGetNearbyStops(latLngBoundsOuterBox);
        }
    }

    /**
     * clears all the Stop markers in the map
     */
    private void clearStopMarkers() {
        if (mStopMarkers!=null && !mStopMarkers.isEmpty()){
            for (Marker marker : mStopMarkers){
                marker.remove();
            }
        }
    }

    /**
     * clear Polylines from map
     */
    private void clearPolyline(){
        if (mPolyLines != null && !mPolyLines.isEmpty()){
            for (Polyline polyline : mPolyLines){
                polyline.remove();
            }
        }
    }

    private void callGetJourney(Marker marker) {
        mProgressBar.setVisibility(View.VISIBLE);
        if (marker!=null){

            LatLng latLngArrival =marker.getPosition();
            getCallController.startGetJourneys(latLngArrival, mLatLngPosition);
        }

    }




    private void manageSession() {
        if (mIsUserLogged){
            signOut();
        }else {
            singIn();
        }
    }

    private void singIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GoogleSignInUtils.RC_SIGN_IN);
        writeToPreferencesSkipLogIn();
    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mIsUserLogged=false;
                mMenuItemLog.setIcon(getResources().getDrawable(R.drawable.ic_person_black_24dp,null));
                mMenuItemLog.setTitle(getString(R.string.log_in_menu_title));
                showSnackBar(getString(R.string.log_out_succsesfull));
            }
        });
        writeToPreferencesSkipLogIn();

    }

    private void manageFavorites(){

        if (mIsFavorite){
            int sqlCode= deleteFavoriteQuery(this,mFavoriteId);
            if (sqlCode >0){
                mIsFavorite=false;


            }else {
                Log.e(MAP_ACTIVITY_TAG,getString(R.string.error_deleting_fav)+sqlCode);

            }
        }else {

            ContentValues contentValues =createFavoriteContentValues();
            Uri uri = addFavoriteQuery(this,contentValues);
            if (uri!=null){
                long returnedId = ContentUris.parseId(uri);
                if (returnedId>=0){
                    mIsFavorite=true;
                    mFavoriteId=returnedId;
                }else {
                    Log.e(MAP_ACTIVITY_TAG,getString(R.string.insert_failed)+uri);
                }
            }
        }
        changeIconColor(mIsFavorite);
        //We update the favorites Widgets
        UpdateWidgetService.startActionUpdatePlantWidgets(this);

    }

    /**
     * start Favorites Activity
     */
    private void goToFavorites(){
        Intent intent = new Intent(this,FavoritesActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        } else {
            startActivity(intent);
        }
    }

    /**
     * this method allow to find an favorite by their lat and lng
     * @param args lat and lng to find the favorite
     */
    private void queryAllByLatLngFavoritesDB(Bundle args) {
        if ( getSupportLoaderManager().getLoader(FAVORITE_READ_BY_LAT_LNG_LOADER)!=null){
            getSupportLoaderManager().restartLoader(FAVORITE_READ_BY_LAT_LNG_LOADER,args,this);
        }
        else {
            getSupportLoaderManager().initLoader(FAVORITE_READ_BY_LAT_LNG_LOADER,args,this);
        }


    }

    /**
     * Not yet implemented
     * This method allow to find a favorite by their Id
     * @param args Only contains ID
     */
    private void getFavoriteById(Bundle args){
        if ( getSupportLoaderManager().getLoader(FAVORITE_READ_BY_ID_LOADER)!=null){
            getSupportLoaderManager().restartLoader(FAVORITE_READ_BY_ID_LOADER,args,this);
        }
        else {
            getSupportLoaderManager().initLoader(FAVORITE_READ_BY_ID_LOADER,args,this);
        }

    }

    /**
     * Create the ContentValues to insert the favorite in the DB
     * @return favorite content value
     */
    private ContentValues createFavoriteContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoritesContract.favoritesEntry.COLUMN_LAT,mClickMarker.getPosition().latitude);
        contentValues.put(FavoritesContract.favoritesEntry.COLUMN_LNG,mClickMarker.getPosition().longitude);
        Object object = mMarkerInfoMap.get(mClickMarker.getId());
        addSpecificInfo(contentValues,object);
        return contentValues;
    }

    private void addSpecificInfo(ContentValues contentValues, Object object){

        if(object instanceof PhysicalStop) {
            PhysicalStop physicalStop= (PhysicalStop)object;
            contentValues.put(FavoritesContract.favoritesEntry.COLUMN_NAME,physicalStop.getName());
            contentValues.put(FavoritesContract.favoritesEntry.COLUMN_CATEGORY,getString(R.string.category_arret));
        }

    }

    private void changeIconColor(Boolean isFavorite){
        if (isFavorite){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCenterAddFavoritesFAB.setBackgroundTintList(
                        ColorStateList.valueOf(getResources().getColor(R.color.colorAccent,null)));
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCenterAddFavoritesFAB.setBackgroundTintList(
                        ColorStateList.valueOf(getResources().getColor(R.color.white,null)));
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int optionSelected=item.getItemId();
        switch (optionSelected) {
            case R.id.news_feed:

                break;
            case R.id.about_this_app:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);

                break;
            case R.id.settings:

                break;
            case R.id.log:
                manageSession();
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (mInfoWindowV.getVisibility() == View.VISIBLE){
            hideInfoWindow();
        }else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> loader =null;
        switch (id){
            case FAVORITE_READ_BY_LAT_LNG_LOADER:
                loader = new DbReadByLatLongNameAsyncTask(MapActivity.this,MAP_ACTIVITY_TAG,args);
                break;
            case FAVORITE_READ_BY_ID_LOADER:
                loader = new DbReadByIDAsyncTask(this,MAP_ACTIVITY_TAG,
                        args != null ? args.getInt(ID_TAG) : 0);
                break;
            default:
                Log.e(MAP_ACTIVITY_TAG,getString(R.string.on_create_loader)+getString(R.string.unknown_loader)+id);
        }

        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data==null) return;
        List<FavoriteEntry> list = cursorToEntryList(data);
        switch(loader.getId()){

            case FAVORITE_READ_BY_LAT_LNG_LOADER:
                onLoadFinishedReadByLatLng(list);
                break;

            case FAVORITE_READ_BY_ID_LOADER:
                onLoadFinishedByID(list);

                break;
            default:
                Log.e(MAP_ACTIVITY_TAG,getString(R.string.on_load_finished)+getString(R.string.unknown_loader)+loader.getId());

        }
    }
    private void onLoadFinishedByID(List<FavoriteEntry> entryList) {
        if (testSingleFavoriteArraySize(entryList)){
            LatLngBounds latLngBounds = MapUtils.calculateBoundingBox(RADIUS_METERS_ZOOM, mLatLngPosition);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
        }

    }

    private void onLoadFinishedReadByLatLng(List<FavoriteEntry> entryList) {
        if (entryList!=null && testSingleFavoriteArraySize(entryList)){
            mIsFavorite=true;
            mFavoriteId=entryList.get(0).getFavId();
            changeIconColor(true);
        }
        else {
            //the Entry does not exist in the DB or more than one
            mIsFavorite=false;
            mFavoriteId=-1;
            changeIconColor(false);
        }
    }



    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Google Sign In was successful, authenticate with FireBase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //Message Log in Successful
                showSnackBar(getString(R.string.log_in_successful));
                mIsUserLogged=true;


            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(LOG_IN_MENU_TAG, getString(R.string.log_in_failed) + " code: " + e.getStatusCode());

                //Message Sign in Failed
                showSnackBar(getString(R.string.log_in_failed) + " " + getString(R.string.please_try_again));

            }
        }
    }

    private void showSnackBar(String msg){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),msg,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * Method to save the choice
     */
    private void writeToPreferencesSkipLogIn(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.not_logged), false);
        editor.apply();
    }


}
