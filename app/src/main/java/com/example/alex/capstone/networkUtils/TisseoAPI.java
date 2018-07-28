package com.example.alex.capstone.networkUtils;

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
    Call<ResponseBody> getNearbyStops(@QueryMap Map<String,String> options);

}
