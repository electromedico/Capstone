
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlacesList implements Parcelable
{

    @SerializedName("place")
    @Expose
    private List<Place> place = new ArrayList<>();
    public final static Parcelable.Creator<PlacesList> CREATOR = new Creator<PlacesList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PlacesList createFromParcel(Parcel in) {
            return new PlacesList(in);
        }

        public PlacesList[] newArray(int size) {
            return (new PlacesList[size]);
        }

    }
    ;

    protected PlacesList(Parcel in) {
        in.readList(this.place, (com.example.alex.capstone.model.Place.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlacesList() {
    }

    /**
     * 
     * @param place
     */
    public PlacesList(List<Place> place) {
        super();
        this.place = place;
    }

    public List<Place> getPlace() {
        return place;
    }

    public void setPlace(List<Place> place) {
        this.place = place;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(place);
    }

    public int describeContents() {
        return  0;
    }

}
