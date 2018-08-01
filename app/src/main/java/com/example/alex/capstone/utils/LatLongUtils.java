package com.example.alex.capstone.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;


public class LatLongUtils{
    static double RADIUS_METERS =1000;


    public static LatLngBounds calculateBondingBox(LatLng center){

        double distanceFromCenterToCorner = RADIUS_METERS * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }

}
