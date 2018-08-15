package com.example.alex.capstone.networkUtils;

import com.example.alex.capstone.model.GetStopPointsQuery;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface TisseoAPI {

    String STOP_POINTS_PATH ="stop_points.json";
    String PLACES_PATH= "places.json";
    String STOP_SCHEDULES_PATH = "stops_schedules.json";
    String JOURNEYS_PATH = "journeys.json";

    @GET(STOP_POINTS_PATH)
    Call<GetStopPointsQuery> getNearbyStops(@QueryMap Map<String,String> options);

    @GET(STOP_SCHEDULES_PATH)
    Call<ResponseBody> getStopSchedule(@QueryMap Map<String,String> options);

    @GET(JOURNEYS_PATH)
    Call<ResponseBody> getJourney(@QueryMap Map<String,String> options);

    @GET(PLACES_PATH)
    Call<ResponseBody> getPlaces(@QueryMap Map<String,String> options);



}
