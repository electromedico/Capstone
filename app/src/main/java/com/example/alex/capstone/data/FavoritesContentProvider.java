package com.example.alex.capstone.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.alex.capstone.data.FavoritesContract.favoritesEntry.buildFavoritesUriWithID;

public class FavoritesContentProvider extends ContentProvider {

    private static final int CODE_FAVORITES = 100;
    private static final int CODE_FAVORITES_BY_ID = 110;
    private static final int CODE_FAVORITES_BY_LAT_LNG = 120;

    private static final UriMatcher URI_MATCHER = buildUriMatcher();

    private FavoritesDbHelper favoritesDbHelper;

    @Override
    public boolean onCreate() {
        favoritesDbHelper = new FavoritesDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        int match = URI_MATCHER.match(uri);
        switch (match){
            case CODE_FAVORITES:

                cursor=favoritesDbHelper.getReadableDatabase().query(
                        FavoritesContract.favoritesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case CODE_FAVORITES_BY_ID:
                String id = uri.getPathSegments().get(1);
                cursor= favoritesDbHelper.getReadableDatabase().query(
                  FavoritesContract.favoritesEntry.TABLE_NAME,
                        projection,
                        FavoritesContract.favoritesEntry.COLUMN_ID+"=?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder
                );
                break;
            default: new UnsupportedOperationException("Unknown uri: " + uri);

        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri returnedUri;
        int match = URI_MATCHER.match(uri);

        switch (match){
            case CODE_FAVORITES:
                Long id = favoritesDbHelper.getWritableDatabase().insert(
                        FavoritesContract.favoritesEntry.TABLE_NAME,
                        null,
                        values);
                returnedUri = buildFavoritesUriWithID(id);
                getContext().getContentResolver().notifyChange(returnedUri,null);
                break;

            default:throw new UnsupportedOperationException("not yet implemented");
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnedUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted;
        int match = URI_MATCHER.match(uri);
        String id = uri.getPathSegments().get(1);

        switch (match){
            case CODE_FAVORITES_BY_ID:
                numRowsDeleted= favoritesDbHelper.getWritableDatabase().delete(
                        FavoritesContract.favoritesEntry.TABLE_NAME,
                        FavoritesContract.favoritesEntry.COLUMN_ID+"=?",
                        new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (numRowsDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static UriMatcher buildUriMatcher(){
        final  UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FavoritesContract.CONTENT_AUTHORITY;
        matcher.addURI(authority,FavoritesContract.PATH_FAVORITES,CODE_FAVORITES);
        matcher.addURI(authority,FavoritesContract.PATH_FAVORITES+"/#",CODE_FAVORITES_BY_ID);
        return matcher;

    }
}
