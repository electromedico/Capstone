package com.example.alex.capstone.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtils {

    /**
     * Transform location into LatLang
     * @param location location
     * @return Location in LatLang format
     */
    public static LatLng locationToLatLong (Location location){
        if (location==null) return null;
        Double y = location.getLongitude();
        Double x = location.getLatitude();
        return new LatLng(x, y);
    }
}
