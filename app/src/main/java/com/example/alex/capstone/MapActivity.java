package com.example.alex.capstone;

import android.Manifest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alex.capstone.adapters.infoWindowAdapters.InfoWindowsRecyclerViewAdapter;
import com.example.alex.capstone.adapters.searchViewAdapters.SearchSuggestionsAdapter;
import com.example.alex.capstone.data.FavoriteEntry;
import com.example.alex.capstone.data.FavoritesContract;
import com.example.alex.capstone.data.dataUtils.DbReadAllAsyncTask;
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
import com.example.alex.capstone.networkUtils.GetCallController;
import com.example.alex.capstone.networkUtils.OnTaskCompleted;
import com.example.alex.capstone.networkUtils.callControlers.GetNearbyStopsController;
import com.example.alex.capstone.utils.LocationUtils;
import com.example.alex.capstone.utils.MapUtils;
import com.example.alex.capstone.widgetUtils.UpdateWidgetService;
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
import static com.example.alex.capstone.adapters.searchViewAdapters.SearchSuggestionsAdapter.SUGESTION_CURSOR_FIELDS;
import static com.example.alex.capstone.utils.DataUtils.cursorToEntryList;
import static com.example.alex.capstone.utils.MapUtils.RADIUS_METERS_ZOOM;
import static com.example.alex.capstone.utils.MapUtils.calculateBoundingBox;
import static com.example.alex.capstone.widgetUtils.FavoritesWidgetProvider.WIDGET_ID_KEY;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, OnTaskCompleted, GoogleMap.OnCameraIdleListener, NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    //Constant for PERMISSIONS_REQUEST
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;

    // Constants for logging and referring to a unique loader
    private static final String MAP_ACTIVITY_TAG = MainActivity.class.getSimpleName();
    private static final int FAVORITE_READ_LOADER = 1;
    private static final int FAVORITE_READ_BY_ID_LOADER = 2;
    private static final int FAVORITE_READ_BY_LAT_LNG_LOADER = 3;

    //Constants for Args in Loader
    public static final String LAT_TAG = "LAT";
    public static final String LNG_TAG = "LNG";
    private static final String ID_TAG = "ID";


    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocation = null;
    private LatLng mLatLngPosition;
    private List<PhysicalStop> mlistPhysicalStops;
    private CameraPosition cameraPosition;
    private Marker mClickMarker;
    private GetCallController getCallController;
    private String mSelectedStopAreaID;
    private List<Place> placeList;
    private List<Marker> mStopMarkers = new ArrayList<>();
    private List<Polyline> mPolyLines = new ArrayList<>();
    private HashMap<String, Object> mMarkerInfoMap = new HashMap<>();
    private boolean mIsFavorite = false;
    private boolean mCalledByWidget = false;
    private PhysicalStop mPhysicalStop;
    private long mFavoriteId = -1;
    private FavoriteEntry favoriteEntry;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private InfoWindowsRecyclerViewAdapter recyclerViewAdapter;


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
            if (intentExtras.containsKey(WIDGET_ID_KEY)) {
                mCalledByWidget = true;
                Gson gson = new Gson();
                favoriteEntry = gson.fromJson(intentExtras.getString(getString(R.string.favorite_json_key)), FavoriteEntry.class);
            }
        }

        getCallController = new GetCallController(this, this);

        //InfoWindow Recycler view SetUp
        RecyclerView mRecyclerView = findViewById(R.id.bus_schedules_info_window_rv);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)

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
                getAllFavorites();
            }
        });

        mNavigationIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mClickMarker == null) {
                    Toast.makeText(MapActivity.this, "Choose a point in the map", LENGTH_LONG).show();
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
                    Log.e(getString(R.string.on_click_go_to_bus_stop),
                            getString(R.string.mclickmarker_null));
                    Toast.makeText(MapActivity.this,"Ops something went wrong",LENGTH_LONG);
                } else {
                    callGetJourney(mClickMarker);
                    hideInfoWindow();
                }
            }
        });

    }

    /**
     * On create method to setup the SerachView
     */
    private void setUpSearchView() {
        //Set the suggestions adapter
        searchView.setSuggestionsAdapter(
                new SearchSuggestionsAdapter(this,
                        R.layout.suggestions_item_layout,
                        new MatrixCursor(SUGESTION_CURSOR_FIELDS),
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
                    searchView.getSuggestionsAdapter().swapCursor(new MatrixCursor(SUGESTION_CURSOR_FIELDS));
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

        enableGoogleMapMyLocation();
        //If the activity was called by the widget we focus the favorite location instead
        if (mCalledByWidget) {
            moveCamera(favoriteEntry.getLatLng());
            //Only used for on create
            mCalledByWidget = false;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(getString(R.string.camera_key), mGoogleMap.getCameraPosition());

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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(getString(R.string.camera_key))) {
            cameraPosition = savedInstanceState.getParcelable(getString(R.string.camera_key));
        }
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
            MatrixCursor suggestionsCursor = new MatrixCursor(SUGESTION_CURSOR_FIELDS);

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
                Log.e(getString(R.string.load_journey),getString(R.string.chunks_is_empty));
            }
        }
        else {
            Toast.makeText(this,"no journeys found",LENGTH_LONG).show();
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
            mlistPhysicalStops = physicalStops.getPhysicalStop();
            for (PhysicalStop physicalStop : mlistPhysicalStops) {

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
            }
        } else {
            Toast.makeText(this, getString(R.string.no_stops), LENGTH_LONG).show();
        }

    }

    private boolean moveCamera(LatLng latLng){
        /* In some cases the map view has not being loaded angd there is an exception.
        to avoid this we pass the dimensions of the map screnn*/
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

        if (latLng!=null){
            LatLngBounds latLngBounds = calculateBoundingBox(MapUtils.RADIUS_METERS_ZOOM, latLng);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds,width,height,padding));
            return true;

        }
        else {
            Log.e(getString(R.string.map_act_move_camera),getString(R.string.lat_lng_null));
            return false;
        }

    }

    /***
     * Method to load the information in the Info Window
     * @param stopArea
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
        mBottomBarV.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mInfoWindowV,"translationY",100f);
        objectAnimator.setDuration(1000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mInfoWindowV.animate().translationY(mInfoWindowV.getHeight()).setDuration(1000).start();
        //mInfoWindowV.setVisibility(View.GONE);
        mCenterAddFavoritesFAB.setVisibility(View.GONE);
    }


    @Override
    public void onCameraIdle() {

        clearStopMarkers();
        if (mGoogleMap.getCameraPosition().zoom > Float.parseFloat(getString(R.string.map_zoom_level))){
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
     * clear polylines from map
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
            case FAVORITE_READ_LOADER:
                loader = new DbReadAllAsyncTask(this,MAP_ACTIVITY_TAG);
                break;
            case FAVORITE_READ_BY_ID_LOADER:
                loader = new DbReadByIDAsyncTask(this,MAP_ACTIVITY_TAG,args.getInt(ID_TAG));
                break;
            default: new UnsupportedOperationException("Unknown Loader");
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

            case FAVORITE_READ_LOADER:
                if (data.getCount()<=0){
                    //todo no favs
                }else {
                    //todo all favs
                    list.size();
                }
                break;

            case FAVORITE_READ_BY_ID_LOADER:
                onLoadFinishedByID(list);

                break;
            default: new UnsupportedOperationException("Unknown Loader");

        }
    }

    private void onLoadFinishedByID(List<FavoriteEntry> entryList) {
        if (testSingleFavoriteArraySize(entryList)){
            LatLngBounds latLngBounds = MapUtils.calculateBoundingBox(RADIUS_METERS_ZOOM, mLatLngPosition);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
            //getCallController.startGetStopSchedules();

        }

    }

    private void onLoadFinishedReadByLatLng(List<FavoriteEntry> entryList) {
        if (testSingleFavoriteArraySize(entryList)){
            mIsFavorite=true;
            mFavoriteId=entryList.get(0).getFavId();
            changeIconColor(mIsFavorite);
        }
        else {
            //the Entry does not exist in the DB or more than one
            mIsFavorite=false;
            mFavoriteId=-1;
            changeIconColor(mIsFavorite);
        }
    }

    /**
     * for the query that should return only one element
     * @param entryList list recupered by the query
     * @return boolean true if single element
     */
    private boolean testSingleFavoriteArraySize(List<FavoriteEntry> entryList){
        if ( entryList==null || entryList.size()<=0){
        return false;
        }
        else if (entryList.size()>1){
            Log.e(" +1 fav same location", String.valueOf(entryList.size()));
            return false;
        }
        else {
            return true;
        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    private void manageFavorites(){

        if (mIsFavorite){
            int sqlCode=deleteFavorite(mFavoriteId);
            if (sqlCode >0){
                changeIconColor(false);
                mIsFavorite=false;
            }else {
                Log.e("DELETE FAILED","something went wrong deleting the favorite");

            }
        }else {
           Uri uri =addFavorite();
            if (uri!=null){
                long returnedId = ContentUris.parseId(uri);
                if (returnedId>=0){
                    mIsFavorite=true;
                    changeIconColor(true);
                    mFavoriteId=returnedId;
                }else {
                    Log.e("INSERT FAILED","something went wrong inserting the favorite");
                }
            }
        }

        //We update the favorites Widgets
        UpdateWidgetService.startActionUpdatePlantWidgets(this);

    }

    private void getAllFavorites(){
       if (getSupportLoaderManager().getLoader(FAVORITE_READ_LOADER)!=null){
           getSupportLoaderManager().restartLoader(FAVORITE_READ_LOADER,null,this);
       }else {
           getSupportLoaderManager().initLoader(FAVORITE_READ_LOADER,null,this);
       }

    }

    /**
     * this method allow to find an favorite by ther lat and lng
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
     * This method allow to find a favorite by thier Id
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
     *Allow to delete the favorite by its ID
     * @param id favorite ID
     */
    private int deleteFavorite(Long id){
        return  getContentResolver().delete(
                FavoritesContract.favoritesEntry.buildFavoritesUriWithID(id),
                null,
                null
        );

    }

    private Uri addFavorite(){
        ContentValues contentValues = createFavoriteContentValues();
        return  getContentResolver().insert(
                FavoritesContract.favoritesEntry.CONTENT_URI,
                contentValues
        );



    }

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
            contentValues.put(FavoritesContract.favoritesEntry.COLUMN_CATEGORIE,getString(R.string.categorie_arret));
        }

    }

    private void changeIconColor(Boolean isFavorite){

        if (isFavorite){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCenterAddFavoritesFAB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent,null)));
            }
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCenterAddFavoritesFAB.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white,null)));
            }

        }
    }


}
