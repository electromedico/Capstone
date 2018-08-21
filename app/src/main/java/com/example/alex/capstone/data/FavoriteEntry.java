package com.example.alex.capstone.data;

import com.google.android.gms.maps.model.LatLng;

public class FavoriteEntry {

    private long favId;
    private String lat;
    private String lng;
    private String name;
    private String category;

    public FavoriteEntry(int favId, String lat, String lng, String name, String category) {
        this.favId = favId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.category = category;
    }

    public LatLng getLatLng(){
        if (lat!=null && lng !=null){
            return new LatLng(Double.valueOf(lat),
                    Double.valueOf(lng));
        }
        return null;
    }

    public FavoriteEntry() {
    }

    public long getFavId() {
        return favId;
    }

    public void setFavId(long favId) {
        this.favId = favId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
