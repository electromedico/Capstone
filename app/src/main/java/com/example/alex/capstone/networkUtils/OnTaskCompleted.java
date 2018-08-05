package com.example.alex.capstone.networkUtils;

import com.example.alex.capstone.model.Departures;
import com.example.alex.capstone.model.Journey;
import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.model.PlacesList;

public interface OnTaskCompleted {

    void onTaskCompletedGetNearbyStops(PhysicalStops physicalStop);
    void onTaskCompletedGetStopSchedules(Departures departures);
    void onTaskCompletedGetJourneys(Journey journey);
    void onTaskCompletedGetPlaces(PlacesList placesList);

}
