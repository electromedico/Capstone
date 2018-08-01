package com.example.alex.capstone.networkUtils;


import android.content.Context;
import android.util.Log;
import android.webkit.WebView;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.GetQuery;
import com.example.alex.capstone.model.GetStopPointsQuery;
import com.example.alex.capstone.model.PhysicalStop;
import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.utils.ModelUtilis;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetCallController implements Callback<ResponseBody> {

    private Context context;
    private final String apiKey;
    private final TisseoAPI tisseoAPI;
    private final OnTaskCompleted listener;
    private int serviceCalled;
    private static final int GET_NEARBY_STOPS_TAG = 100;
    Gson gson;


    public GetCallController (Context c, OnTaskCompleted listener) {
        context=c;
        apiKey = c.getString(R.string.tisseo_api_key);
        Retrofit client = TisseoApiClient.getClient();
        tisseoAPI= client.create(TisseoAPI.class);
        this.listener=listener;
        gson = new Gson();

    }

    /**
     *method to call getNearbyStops
     */
    public void startGetNearbyStops(LatLngBounds latLngBounds){
        String coordinates= latLngBounds.southwest.longitude
                +","+latLngBounds.southwest.latitude
                +","+latLngBounds.northeast.longitude
                +","+latLngBounds.northeast.latitude;
        serviceCalled= GET_NEARBY_STOPS_TAG;

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
        Call<ResponseBody> call = tisseoAPI.getNearbyStops(parameters);
        call.enqueue(this);




    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        //test response
        if (response.isSuccessful()) {

            try {
                switch (serviceCalled){

                    case GET_NEARBY_STOPS_TAG:
                        getStopPointsQueryTreatment(response.body().string());
                        break;


                    default:
                        break;

                }
            } catch (IOException e) {
                Log.d(context.getString(R.string.retrofit_on_response_log_tag), e.getMessage());
            }


        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d(context.getString(R.string.retrofit_on_failure_log_tag), t.getMessage());

    }

    //Method to treat the getStopPoints response
    private void getStopPointsQueryTreatment(String json) throws IOException {

        GetStopPointsQuery getStopPointsQuery = gson.fromJson(json, GetStopPointsQuery.class);

        //Response content verification
        if (getStopPointsQuery!= null && getStopPointsQuery.getPhysicalStops() != null) {
            //we pass to the listner the list of stops
            listener.onTaskCompletedGetNearbyStops(getStopPointsQuery.getPhysicalStops());
        }
        else {
            throw (new IOException(context.getString(R.string.on_response_excepion)));
        }

    }


}
