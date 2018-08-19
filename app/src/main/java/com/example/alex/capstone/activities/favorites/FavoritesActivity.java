package com.example.alex.capstone.activities.favorites;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alex.capstone.R;
import com.example.alex.capstone.activities.favorites.adapters.recyclerview.EntryOnclickListener;
import com.example.alex.capstone.activities.favorites.adapters.recyclerview.FavoritesRecyclerViewAdapter;
import com.example.alex.capstone.activities.map.MapActivity;
import com.example.alex.capstone.data.FavoriteEntry;
import com.example.alex.capstone.data.dataUtils.DbReadAllAsyncTask;
import com.example.alex.capstone.widget.UpdateWidgetService;
import com.google.android.gms.common.server.BaseApi;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.alex.capstone.data.dataUtils.DbUtils.deleteFavoriteQuery;
import static com.example.alex.capstone.utils.DataUtils.cursorToEntryList;

public class FavoritesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, EntryOnclickListener {

    private static final int FAVORITE_READ_LOADER = 1;
    public static final String FAVORITES_ACTIVITY_TAG =FavoritesActivity.class.getSimpleName();
    private FavoritesRecyclerViewAdapter mRecyclerViewAdapter;
    private HashMap<String,FavoriteEntry> mEntryHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //SetUp the RecyclerView
        RecyclerView mRecyclerView = findViewById(R.id.favorites_recycler_view);
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify an adapter
        mRecyclerViewAdapter = new FavoritesRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        getAllFavorites();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                long deletedId= deleteFavorite(id);
                if (deletedId>0){
                    getAllFavorites();
                    UpdateWidgetService.startActionUpdatePlantWidgets(FavoritesActivity.this);
                }
                else {
                    Toast.makeText(FavoritesActivity.this,getString(R.string.delete_failed),Toast.LENGTH_LONG).show();
                    Log.e(FAVORITES_ACTIVITY_TAG,getString(R.string.error_deleting_fav)+deletedId);
                }

            }
        }).attachToRecyclerView(mRecyclerView);
    }

    /**
     * Method to handle the case where the user has not yet added favorites
     * for the moment only a toast is shown
     */
    private void noFavoritesTreatment() {
        Toast.makeText(this,getString(R.string.no_favorites),Toast.LENGTH_LONG).show();
    }

    /**
     * Method to fill the Favorite Entries Map
     * @param list list of favorites from the DB
     */
    private void addFavoritesToHashMap(List<FavoriteEntry> list) {
        mEntryHashMap= new HashMap<>();
        if (list!=null){
            for(FavoriteEntry entry : list){
                mEntryHashMap.put(String.valueOf(entry.getFavId()),entry);
            }
        }
    }

    /**
     * manage the Loader
     */
    private void getAllFavorites(){
        if (getSupportLoaderManager().getLoader(FAVORITE_READ_LOADER)!=null){
            getSupportLoaderManager().restartLoader(FAVORITE_READ_LOADER,null,this);
        }else {
            getSupportLoaderManager().initLoader(FAVORITE_READ_LOADER,null,this);
        }

    }

    private long deleteFavorite(long id){
        return deleteFavoriteQuery(this,id);
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> loader =null;
        switch (id){
            case FAVORITE_READ_LOADER:
                loader = new DbReadAllAsyncTask(this,FAVORITES_ACTIVITY_TAG);
            default: new UnsupportedOperationException("Unknown Loader");
        }
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data==null){
         noFavoritesTreatment();
         return;
        }
        List<FavoriteEntry> list = cursorToEntryList(data);
        switch(loader.getId()){
            case FAVORITE_READ_LOADER:
                    mRecyclerViewAdapter.setmDataSet(list);
                    addFavoritesToHashMap(list);
                break;

            default: new UnsupportedOperationException("Unknown Loader");

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
    @Override
    public void onListItemClick(String key) {
        //On Favorite Selection, We launch the map activity with the favorite entry
        Intent intent = new Intent(this, MapActivity.class);
        if (mEntryHashMap!=null&& mEntryHashMap.containsKey(key)){
            Gson gson = new Gson();
            String entryJson = gson.toJson(mEntryHashMap.get(key));
            intent.putExtra(FAVORITES_ACTIVITY_TAG,entryJson);
        }else {
            Log.e(FAVORITES_ACTIVITY_TAG,getString(R.string.favorites_map_problem)+key);
        }
        startActivity(intent);


    }
}
