package com.example.alex.capstone.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;


public class LatLongUtils{
    public static double RADIUS_METERS_OUTER_BOX =500;
    public static double RADIUS_METERS_ZOOM =250;


    public static LatLngBounds calculateBoundingBox(Double radius, LatLng center){

        double distanceFromCenterToCorner = radius * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }

}
