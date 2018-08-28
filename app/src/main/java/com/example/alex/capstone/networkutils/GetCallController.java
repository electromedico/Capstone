package com.example.alex.capstone.networkutils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.alex.capstone.R;
import com.example.alex.capstone.model.GetPlacesQuery;
import com.example.alex.capstone.model.GetStopPointsQuery;
import com.example.alex.capstone.model.GetStopSchedulesQuery;
import com.example.alex.capstone.model.getJourneysQueryModel.GetJourneyQuery;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class GetCallController implements Callback<ResponseBody> {


    private final Context context;
    private final String apiKey;
    private final TisseoAPI tisseoAPI;
    private final OnTaskCompleted listener;
    private int serviceCalled;
    private static final int GET_NEARBY_STOPS_TAG = 100;
    private static final int GET_STOP_SCHEDULES_TAG = 200;
    private static final int GET_JOURNEYS_TAG=300;
    private static final int GET_PLACES_TAG = 400 ;
    private final Gson gson;


    public GetCallController (Context c, OnTaskCompleted listener) {
        context=c;
        apiKey = c.getString(R.string.tisseo_api_key);
        Retrofit client = TisseoApiClient.getClient();
        tisseoAPI= client.create(TisseoAPI.class);
        this.listener=listener;
        gson = new Gson();

    }




    public void startGetJourneys(LatLng arrival, LatLng departure){
        serviceCalled= GET_JOURNEYS_TAG;
        String arrivalCoordinates = arrival.longitude+","+arrival.latitude;
        String departureCoordinates = departure.longitude+","+departure.latitude;


        //Map for the query parameters
        Map<String,String> parameters = new HashMap<>();

        parameters.put(context.getString(R.string.departure_place),
                departureCoordinates);

        parameters.put(context.getString(R.string.arrival_place),
                arrivalCoordinates);

        parameters.put(context.getString(R.string.number_results),
                context.getString(R.string.param_number_results));

        parameters.put(context.getString(R.string.display_wording),
                context.getString(R.string.param_display_wording));

        parameters.put(context.getString(R.string.directions_lang),
                context.getString(R.string.param_directions_lang));

        //Api Key parameter
        parameters.put(context.getString(R.string.api_key_param), apiKey);

        //Cal the get method
        Call<ResponseBody> call = tisseoAPI.getJourney(parameters);
        call.enqueue(this);
    }

    public void startGetPlaces(String query){
        serviceCalled= GET_PLACES_TAG;

        //Map for the query parameters
        Map<String,String> parameters = new HashMap<>();

        parameters.put(context.getString(R.string.number_results),
                context.getString(R.string.param_number_results));

        parameters.put(context.getString(R.string.display_best_place),
                context.getString(R.string.param_display_best_place));

        parameters.put(context.getString(R.string.term),
                query);

        parameters.put(context.getString(R.string.directions_lang),
                context.getString(R.string.param_directions_lang));

        //Api Key parameter
        parameters.put(context.getString(R.string.api_key_param), apiKey);

        //Cal the get method
        Call<ResponseBody> call = tisseoAPI.getPlaces(parameters);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
        //test response
        if (response.isSuccessful()) {
            try {
                if (response.body()!=null){
                    String responseBody=response.body().string();
                    switch (serviceCalled){

                        case GET_NEARBY_STOPS_TAG:
                            getStopPointsQueryTreatment(responseBody);
                            break;

                        case GET_STOP_SCHEDULES_TAG:
                            getStopSchedulesTreatment(responseBody);
                            break;

                        case GET_PLACES_TAG:
                            getPlacesTreatment(responseBody);
                            break;

                        case GET_JOURNEYS_TAG:
                            getJourneysTreatment(responseBody);
                        default:
                            break;

                    }
                }

                else  throw new  IOException(context.getString(R.string.on_response_response_body_null));
            } catch (IOException e) {
                Log.e(context.getString(R.string.retrofit_on_response_log_tag), e.getMessage());
            }


        }
    }

    private void getJourneysTreatment(String json) throws  IOException {
        GetJourneyQuery getJourneysQuery = gson.fromJson(json,GetJourneyQuery.class);
        if (getJourneysQuery!= null
                && getJourneysQuery.getRoutePlannerResult() != null
                &&getJourneysQuery.getRoutePlannerResult().getJourneys() != null) {

            //we pass to the listener the list of stops
            listener.onTaskCompletedGetJourneys(getJourneysQuery.getRoutePlannerResult().getJourneys());
        }
        else {
            throw (new IOException(context.getString(R.string.on_response_exception)));
        }

    }

    private void getPlacesTreatment(String json) throws IOException {
        GetPlacesQuery getPlacesQuery = gson.fromJson(json, GetPlacesQuery.class);

        //Response content verification
        if (getPlacesQuery!= null && getPlacesQuery.getPlacesList() != null) {
            //we pass to the listener the list of stops
            listener.onTaskCompletedGetPlaces(getPlacesQuery.getPlacesList());
        }
        else {
            throw (new IOException(context.getString(R.string.on_response_exception)));
        }
    }


    //Method to treat the getStopPoints response
    private void getStopPointsQueryTreatment(String json) throws IOException {

        GetStopPointsQuery getStopPointsQuery = gson.fromJson(json, GetStopPointsQuery.class);

        //Response content verification
        if (getStopPointsQuery!= null && getStopPointsQuery.getPhysicalStops() != null) {
            //we pass to the listener the list of stops
            listener.onTaskCompletedGetNearbyStops(getStopPointsQuery.getPhysicalStops());
        }
        else {
            throw (new IOException(context.getString(R.string.on_response_exception)));
        }

    }

    private void getStopSchedulesTreatment(String json) throws IOException {
        GetStopSchedulesQuery getStopSchedulesQuery = gson.fromJson(json,GetStopSchedulesQuery.class);

        //Response content verification
        if (getStopSchedulesQuery!= null && getStopSchedulesQuery.getDepartures() != null) {
            //we pass to the listener the list of stops
            listener.onTaskCompletedGetStopSchedules(getStopSchedulesQuery.getDepartures());
        }
        else {
            throw (new IOException(context.getString(R.string.on_response_exception)));
        }

    }

    @Override
    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
        Log.e(context.getString(R.string.retrofit_on_failure_log_tag), t.getMessage());

    }
}
