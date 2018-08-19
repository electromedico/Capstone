package com.example.alex.capstone.utils;

import android.database.Cursor;
import android.util.Log;

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

    /**
     * for the query that should return only one element
     * @param entryList list recupered by the query
     * @return boolean true if single element
     */
    public static boolean testSingleFavoriteArraySize(List<FavoriteEntry> entryList){
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
}
