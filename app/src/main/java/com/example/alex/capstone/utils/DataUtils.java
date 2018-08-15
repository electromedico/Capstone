package com.example.alex.capstone.utils;

import android.database.Cursor;

import com.example.alex.capstone.data.FavoriteEntry;
import com.example.alex.capstone.data.FavoritesContract;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {

    public static List<FavoriteEntry> cursorToEntryList(Cursor cursor){
        List<FavoriteEntry> favorites= new ArrayList<>();

        if (cursor.getCount()<=0)return null;
        try {
            while (cursor.moveToNext()){
                FavoriteEntry entry=new FavoriteEntry();
                entry.setFavId(cursor.getInt(
                        cursor.getColumnIndex(FavoritesContract.favoritesEntry.COLUMN_ID)));
                entry.setCategorie(cursor.getString(
                        cursor.getColumnIndex(FavoritesContract.favoritesEntry.COLUMN_CATEGORIE)));
                entry.setLat(cursor.getString(
                        cursor.getColumnIndex(FavoritesContract.favoritesEntry.COLUMN_LAT)));
                entry.setLng(cursor.getString(
                        cursor.getColumnIndex(FavoritesContract.favoritesEntry.COLUMN_LNG)));
                entry.setName(cursor.getString(
                        cursor.getColumnIndex(FavoritesContract.favoritesEntry.COLUMN_NAME)));
                favorites.add(entry);
            }
        }finally {
            cursor.close();
        }
        return favorites;
    }
}
