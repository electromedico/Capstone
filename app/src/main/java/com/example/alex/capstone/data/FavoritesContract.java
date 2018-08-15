package com.example.alex.capstone.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoritesContract {
    public static final String CONTENT_AUTHORITY ="com.example.alex.capstone";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAVORITES="favorites";

    public static final class favoritesEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_FAVORITES)
                .build();

        public static final String TABLE_NAME ="favorites";
        public static final String COLUMN_ID ="_id";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LNG = "lng";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_CATEGORIE = "categorie";
        public static final String COLUMN_TIMESTAMP = "timestamp";

        public static Uri buildFavoritesUriWithID(Long id) {
            return CONTENT_URI.buildUpon()
                    .appendPath(String.valueOf(id))
                    .build();
        }

    }
}
