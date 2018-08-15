package com.example.alex.capstone.data.dataUtils;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.alex.capstone.data.FavoritesContract;

import static com.example.alex.capstone.MapActivity.LAT_TAG;
import static com.example.alex.capstone.MapActivity.LNG_TAG;

public class DbReadByLatLongNameAsyncTask extends AsyncTaskLoader<Cursor> {
    private Cursor cursor;
    private final String TAG;
    private Bundle bundle;

    public DbReadByLatLongNameAsyncTask(@NonNull Context context, String tag,Bundle bundle) {
        super(context);
        TAG=tag;
        if (bundle!= null && bundle.containsKey(LAT_TAG) && bundle.containsKey(LNG_TAG)){
           this.bundle=bundle;
        }


    }

    @Nullable
    @Override
    public Cursor loadInBackground() {
        String WHERE_CLAUSE = FavoritesContract.favoritesEntry.COLUMN_LAT +"="+bundle.get(LAT_TAG)+" AND "
                + FavoritesContract.favoritesEntry.COLUMN_LNG + "="+bundle.get(LNG_TAG);
        try {
            return getContext().getContentResolver().query(
                    FavoritesContract.favoritesEntry.CONTENT_URI,
                    null,
                    WHERE_CLAUSE,
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
        super.onStartLoading();
        if (cursor!=null){
            deliverResult(cursor);
        }else {
            forceLoad();
        }
    }
}
