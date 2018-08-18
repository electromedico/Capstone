package com.example.alex.capstone.utils;

import android.content.Context;

import com.example.alex.capstone.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.SphericalUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MapUtils {

    public static final double RADIUS_METERS_ZOOM =250;

    public static final String WKT_REGEX_STRING = "((((MULTI)?(POINT|LINESTRING|POLYGON)|GEOMETRYCOLLECTION)Z?M?)[\\s]*[(].*[)])";
    private static final String WKT_PREGEX_LINE_STRING_REGEX ="\\((.*?)\\)";
    private static final String WKT_COMA_SPACE_STRING_REGEX =",\\s";
    private static final String REGEX_LINE_STRING = "LINESTRING";
    private static final String REGEX_MULTILINE_STRING = "MULTILINESTRING";
    private static final String REGEX_SPACE = "\\s";
    private static final String REGEX_ENDING_PARENTHESIS_COMA = "\\),\\s\\(";
    private static final String WKT_REGEX_MULTILINE_STRING="(\\(\\()(.*?)(\\)\\))";




    public static LatLngBounds calculateBoundingBox(Double radius, LatLng center){

        double distanceFromCenterToCorner = radius * Math.sqrt(2.0);
        LatLng southwestCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 225.0);
        LatLng northeastCorner =
                SphericalUtil.computeOffset(center, distanceFromCenterToCorner, 45.0);
        return new LatLngBounds(southwestCorner, northeastCorner);
    }


    public static List<LatLng> wtkLineStringToLatLngList(Context c, String wtk) throws IOException {

        List<LatLng> latLngs = new ArrayList<>();
        String[] list;
        if (wtk.contains(REGEX_LINE_STRING)){
            //remove the type of WTK
            String[] s = wtk.split(REGEX_LINE_STRING);
            String extractedText = s[1];
            //extract the coordinates
            Pattern pattern = Pattern.compile(WKT_PREGEX_LINE_STRING_REGEX);
            Matcher matcher = pattern.matcher(extractedText);
            if (matcher.find()){
                String l = matcher.group(1);
                //split all the entries
                list = l.split(WKT_COMA_SPACE_STRING_REGEX);
                for (String sLatLng : list){
                    String[] split = sLatLng.split(REGEX_SPACE);
                    latLngs.add(new LatLng(Double.valueOf(split[1]),Double.valueOf(split[0])));
                }
            }

        }
        else {
            throw (new IOException(c.getString(R.string.wrong_type_of_wtk)));
        }

        return latLngs;
    }

    public static List<List<LatLng>> wtkMultiLineStringToLatLngList(Context c,String wtk) throws IOException {

        List<List<LatLng>> multilineList=  new ArrayList<>();
        String extractedText;

        Matcher matcher;
        Pattern pattern;

        if (wtk.contains(REGEX_MULTILINE_STRING)){

            //subtract the identifier
            String[] s = wtk.split(REGEX_MULTILINE_STRING);
            extractedText= s[1];

            //Subtract the double parenthesis
            pattern= Pattern.compile(WKT_REGEX_MULTILINE_STRING);
            matcher = pattern.matcher(extractedText);
            if (matcher.find()){
                //subtract the ending double parenthesis
                extractedText = matcher.group(2);

                String[] list = extractedText.split(REGEX_ENDING_PARENTHESIS_COMA);

                for (String lineString : list){
                    //separate all the entries
                    List<LatLng> latLngs = new ArrayList<>();
                    String[] entries = lineString.split(WKT_COMA_SPACE_STRING_REGEX);
                    for (String sLatLng : entries){
                        //split and create all the LatLng
                        String[] split = sLatLng.split(REGEX_SPACE);
                        latLngs.add(new LatLng(Double.valueOf(split[1]),Double.valueOf(split[0])));
                    }
                    multilineList.add(latLngs);
                }
            }

        }
        else {
            throw (new IOException(c.getString(R.string.wrong_type_of_wtk)));
        }

        return multilineList;
    }
}
