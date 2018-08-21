package com.example.alex.capstone.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.alex.capstone.R;

public class UpdateWidgetService extends IntentService {

    private static final String ACTION_UPDATE_FAVORITES_WIDGETS = "com.example.android.capstopne.action.update_widgets";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public UpdateWidgetService() {
        super("UpdateWidgetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_UPDATE_FAVORITES_WIDGETS.equals(action)){
                handleActionUpdateFavorites();
            }
        }
    }

    /**
     * Starts this service to perform UpdateFavoritesWidgets action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     * @param context this
     */
    public static void startActionUpdatePlantWidgets(Context context) {
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.setAction(ACTION_UPDATE_FAVORITES_WIDGETS);
        context.startService(intent);
    }

    /**
     * We notify the data has changed
     */
    private void handleActionUpdateFavorites() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,FavoritesWidgetProvider.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);
        FavoritesWidgetProvider.updateAllWidgets(this,appWidgetManager,appWidgetIds);
    }
}
