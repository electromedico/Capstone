package com.example.alex.capstone.networkUtils;


import android.content.Context;
import android.util.Log;

import com.example.alex.capstone.R;
import com.example.alex.capstone.model.GetQuery;
import com.example.alex.capstone.utils.ModelUtilis;
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

    public GetCallController (Context c, OnTaskCompleted listener) {
        context=c;
        apiKey = c.getString(R.string.tisseo_api_key);
        Retrofit client = TisseoApiClient.getClient();
        tisseoAPI= client.create(TisseoAPI.class);
        this.listener=listener;

    }

    /**
     *method to call getNearbyStops
     */
    public void startGetNearbyStops(String coordinates){
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

        if (response.isSuccessful()) {
            try {

                //utility method to adapt the JSON string form the API to the model
                JSONObject jsonObject=ModelUtilis.changeStopToStoppoint(response.body().string(),context);

                Gson gson = new Gson();
                GetQuery getQuery = gson.fromJson(jsonObject.toString(),GetQuery.class);

                if (getQuery!= null && getQuery.object!= null){

                    listener.onTaskCompleted(getQuery.object);
                }
                else {
                    IOException e = new IOException(context.getString(R.string.on_response_excepion));
                }


            } catch (IOException e) {
                Log.d(context.getString(R.string.retrofit_on_response_log_tag), e.getMessage());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        Log.d(context.getString(R.string.retrofit_on_failure_log_tag), t.getMessage());

    }


}
