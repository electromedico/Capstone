package com.example.alex.capstone.data.dataUtils;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.alex.capstone.data.FavoritesContract;


public class DbReadAllAsyncTask extends AsyncTaskLoader<Cursor> {

    private final Cursor cursor=null;
    private final String TAG;

    public DbReadAllAsyncTask(Context context, String tag) {
        super(context);
        TAG=tag;
    }

    @Override
    protected void onStartLoading() {
        if (cursor !=null){
            deliverResult(cursor);
        }else {
            forceLoad();
        }

    }

    @Override
    public Cursor loadInBackground() {
        try {
            return getContext().getContentResolver().query(
                    FavoritesContract.favoritesEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    FavoritesContract.favoritesEntry.COLUMN_CATEGORY,
                    null
            );
        }catch (Exception e){
            Log.e(TAG,"Failed to asynchronously load data.");
            e.printStackTrace();
            return null;
        }
    }

}
