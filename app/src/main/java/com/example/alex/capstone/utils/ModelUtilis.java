package com.example.alex.capstone.utils;

import android.content.Context;

import com.example.alex.capstone.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelUtilis {

    public static JSONObject changeStopToStoppoint(String json, Context context) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has(context.getString(R.string.route_planner_result_json_key))){
            Object object = jsonObject.getJSONObject(context.getString(R.string.stop_json_key));
            jsonObject.remove(context.getString(R.string.stop_json_key));
            jsonObject.accumulate(context.getString(R.string.stoppoint_json_key),object);
        }
        return jsonObject;

    }

}
