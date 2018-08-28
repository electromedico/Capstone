package com.example.alex.capstone.networkutils.callControlers;

import android.content.Context;
import android.util.Log;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.GetStopSchedulesQuery;
import com.example.alex.capstone.networkutils.OnTaskCompleted;
import com.example.alex.capstone.networkutils.TisseoAPI;
import com.example.alex.capstone.networkutils.TisseoApiClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetStopSchedulesController implements Callback<GetStopSchedulesQuery> {


    private final Context context;
    private final String apiKey;
    private final TisseoAPI tisseoAPI;
    private final OnTaskCompleted listener;

    public GetStopSchedulesController(Context context, OnTaskCompleted listener) {
        this.context = context;
        apiKey = context.getString(R.string.tisseo_api_key);
        Retrofit client = TisseoApiClient.getClient();
        tisseoAPI= client.create(TisseoAPI.class);
        this.listener = listener;
    }

    public void startGetStopSchedulesById(String stopPointId){

        //Map for the query parameters
        Map<String,String> parameters = new HashMap<>();

        //Display coordinates
        parameters.put(context.getString(R.string.stop_point_id),
                stopPointId);

        parameters.put(context.getString(R.string.time_table_by_area),context.getString(R.string.param_time_table_by_area));

        //Api Key parameter
        parameters.put(context.getString(R.string.api_key_param), apiKey);

        //Cal the get method
        Call<GetStopSchedulesQuery> call = tisseoAPI.getStopSchedule(parameters);
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<GetStopSchedulesQuery> call, Response<GetStopSchedulesQuery> response) {
        if (response.isSuccessful()){
            GetStopSchedulesQuery getStopSchedulesQuery = response.body();

            //Response content verification
            if (getStopSchedulesQuery!= null && getStopSchedulesQuery.getDepartures() != null) {
                //we pass to the listener the list of stops
                listener.onTaskCompletedGetStopSchedules(getStopSchedulesQuery.getDepartures());
            }
            else {
                try {
                    throw (new IOException(context.getString(R.string.on_response_exception)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<GetStopSchedulesQuery> call, Throwable t) {
        Log.e(context.getString(R.string.retrofit_on_failure_log_tag), t.getMessage());

    }
}
