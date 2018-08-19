package com.example.alex.capstone.activities.map.adapters.searchview;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;

public class SearchSuggestionsAdapter extends SimpleCursorAdapter {

    public static final String[] SUGESTION_CURSOR_FIELDS = { "_id", "result" };
    private static final String[] mVisible = { "result" };

    public SearchSuggestionsAdapter(Context context, int layout, Cursor c, int[] to) {
        super(context, layout, c, mVisible, to, 0);
    }
}
