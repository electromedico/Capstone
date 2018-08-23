package com.example.alex.capstone.data.dataUtils;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.alex.capstone.data.FavoritesContract;

public class DbReadByIDAsyncTask extends AsyncTaskLoader<Cursor> {
    private final Cursor cursor=null;
    private final String TAG;
    private int id;

    public DbReadByIDAsyncTask(@NonNull Context context, String tag, int id) {
        super(context);
        TAG=tag;
        this.id=id;
    }

    @Nullable
    @Override
    public Cursor loadInBackground() {
        try {
            return getContext().getContentResolver().query(
                    FavoritesContract.favoritesEntry.buildFavoritesUriWithID((long) id),
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }catch (Exception e){
            Log.e(TAG,"Failed to asynchronously load data.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        if (cursor !=null){
            deliverResult(cursor);
        }else {
            forceLoad();
        }

    }

}
