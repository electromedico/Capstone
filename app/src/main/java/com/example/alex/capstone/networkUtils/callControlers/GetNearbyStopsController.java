package com.example.alex.capstone.networkUtils.callControlers;

import android.content.Context;
import android.util.Log;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.GetStopPointsQuery;
import com.example.alex.capstone.networkUtils.OnTaskCompleted;
import com.example.alex.capstone.networkUtils.TisseoAPI;
import com.example.alex.capstone.networkUtils.TisseoApiClient;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetNearbyStopsController implements Callback<GetStopPointsQuery>{

    private final Context context;
    private final String apiKey;
    private final TisseoAPI tisseoAPI;
    private final OnTaskCompleted listener;


    public GetNearbyStopsController(Context c, OnTaskCompleted listener) {
        context=c;
        apiKey = c.getString(R.string.tisseo_api_key);
        Retrofit client = TisseoApiClient.getClient();
        tisseoAPI= client.create(TisseoAPI.class);
        this.listener=listener;
    }

    /**
     *method to call getNearbyStops
     */
    public void startGetNearbyStops(LatLngBounds latLngBounds){

        String coordinates= latLngBounds.southwest.longitude
                +","+latLngBounds.southwest.latitude
                +","+latLngBounds.northeast.longitude
                +","+latLngBounds.northeast.latitude;

        //Map for the query parameters
        Map<String,String> parameters = new HashMap<>();

        //Display coordinates
        parameters.put(context.getString(R.string.display_coord_xy),
                context.getString(R.string.display_coord_xy_value));

        //Display Destination Parameter
        parameters.put(context.getString(R.string.display_destinations),
                context.getString(R.string.display_destinations_value));
        //BBOX Parameter
        parameters.put(context.getString(R.string.bbox),
                coordinates);

        //Api Key parameter
        parameters.put(context.getString(R.string.api_key_param), apiKey);

        //Cal the get method
        Call<GetStopPointsQuery> call = tisseoAPI.getNearbyStops(parameters);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<GetStopPointsQuery> call, Response<GetStopPointsQuery> response) {
        //test response
        if (response.isSuccessful()) {
            GetStopPointsQuery getStopPointsQuery = response.body();

            //Response content verification
            if (getStopPointsQuery!= null && getStopPointsQuery.getPhysicalStops() != null) {
                //we pass to the listener the list of stops
                listener.onTaskCompletedGetNearbyStops(getStopPointsQuery.getPhysicalStops());
            }
            else {

                Log.e(context.getString(R.string.on_response_retrofit_nearby_stops),context.getString(R.string.on_response_excepion));
            }
        }
    }

    @Override
    public void onFailure(Call<GetStopPointsQuery> call, Throwable t) {

        Log.d(context.getString(R.string.on_response_retrofit_nearby_stops), t.getMessage());

    }
}
