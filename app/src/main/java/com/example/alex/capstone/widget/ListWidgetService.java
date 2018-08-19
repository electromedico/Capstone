package com.example.alex.capstone.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.alex.capstone.activities.map.MapActivity;
import com.example.alex.capstone.R;
import com.example.alex.capstone.data.FavoriteEntry;
import com.example.alex.capstone.data.FavoritesContract;
import com.google.gson.Gson;

import java.util.List;

import static com.example.alex.capstone.widget.FavoritesWidgetProvider.WIDGET_ID_KEY;
import static com.example.alex.capstone.utils.DataUtils.cursorToEntryList;

public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this,intent);
    }

}

class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private final Context context;
    private int mAppWidgetId;
    private List<FavoriteEntry> favoriteEntries;


    public ListRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        if (intent.getExtras() != null){
            mAppWidgetId = Integer.parseInt(intent.getStringExtra(WIDGET_ID_KEY));
        }

    }

    @Override
    public void onCreate() {
        //context.getApplicationContext();

    }

    @Override
    public void onDataSetChanged() {

        Cursor cursor = context.getContentResolver().query(FavoritesContract.favoritesEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    FavoritesContract.favoritesEntry.COLUMN_CATEGORIE,
                    null);
        favoriteEntries = cursorToEntryList(cursor);


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (favoriteEntries== null || favoriteEntries.size()<=0)return 0;
        return favoriteEntries.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_favorites_list_layout);
        if (favoriteEntries != null && favoriteEntries.size()>0){
            FavoriteEntry entry= favoriteEntries.get(position);
            views.setTextViewText(R.id.favorite_widget_tv,entry.getName());

            //Intent to launch the MapActivity on click
            Intent appIntent = new Intent(context, MapActivity.class);
            appIntent.putExtra(WIDGET_ID_KEY,mAppWidgetId);
            Gson gson = new Gson();
            String entryJson = gson.toJson(entry);
            appIntent.putExtra(context.getString(R.string.favorite_json_key),entryJson);
            views.setOnClickFillInIntent(R.id.widget_favorite_container,appIntent);
            return views;
        }
        return null;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}