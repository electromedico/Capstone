package com.example.alex.capstone.data;

import com.google.android.gms.maps.model.LatLng;

public class FavoriteEntry {

    private int favId;
    private String lat;
    private String lng;
    private String name;
    private String categorie;

    public FavoriteEntry(int favId, String lat, String lng, String name, String categorie) {
        this.favId = favId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.categorie = categorie;
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

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
