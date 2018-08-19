package com.example.alex.capstone.data.dataUtils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.example.alex.capstone.data.FavoritesContract;

public class DbUtils {

    /**
     *Allow to delete the favorite by its ID
     * @param id favorite ID or -1(failed)
     */
    public static int deleteFavoriteQuery(Context context, Long id){
        return  context.getContentResolver().delete(
                FavoritesContract.favoritesEntry.buildFavoritesUriWithID(id),
                null,
                null
        );
    }

    /**
     * Add a new Favorite
     * @return Uri
     * @param context
     */
    public static Uri addFavoriteQuery(Context context, ContentValues contentValues) {
        return context.getContentResolver().insert(
                FavoritesContract.favoritesEntry.CONTENT_URI,
                contentValues
        );
    }

}
