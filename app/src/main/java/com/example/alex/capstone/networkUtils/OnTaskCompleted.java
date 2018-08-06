package com.example.alex.capstone.networkUtils;

import com.example.alex.capstone.model.Departures;
import com.example.alex.capstone.model.getJourneysQueryModel.Journey;
import com.example.alex.capstone.model.PhysicalStops;
import com.example.alex.capstone.model.PlacesList;

import java.util.List;

public interface OnTaskCompleted {

    void onTaskCompletedGetNearbyStops(PhysicalStops physicalStop);
    void onTaskCompletedGetStopSchedules(Departures departures);
    void onTaskCompletedGetJourneys(List<Journey> journeys);
    void onTaskCompletedGetPlaces(PlacesList placesList);

}
