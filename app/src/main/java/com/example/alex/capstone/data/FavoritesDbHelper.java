package com.example.alex.capstone.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 2;

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVORITES =
        "CREATE TABLE "+ FavoritesContract.favoritesEntry.TABLE_NAME + " ( " +
                FavoritesContract.favoritesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FavoritesContract.favoritesEntry.COLUMN_LAT + " STRING not null, "+
                FavoritesContract.favoritesEntry.COLUMN_LNG + " STRING not null, " +
                FavoritesContract.favoritesEntry.COLUMN_NAME + " STRING not null, " +
                FavoritesContract.favoritesEntry.COLUMN_CATEGORY + " STRING not null, " +
                FavoritesContract.favoritesEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(SQL_CREATE_FAVORITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.favoritesEntry.TABLE_NAME);
        onCreate(db);

    }
}
