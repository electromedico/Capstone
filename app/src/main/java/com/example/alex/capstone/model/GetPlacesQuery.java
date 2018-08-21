
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPlacesQuery implements Parcelable
{

    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("placesList")
    @Expose
    private PlacesList placesList;
    public final static Parcelable.Creator<GetPlacesQuery> CREATOR = new Creator<GetPlacesQuery>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetPlacesQuery createFromParcel(Parcel in) {
            return new GetPlacesQuery(in);
        }

        public GetPlacesQuery[] newArray(int size) {
            return (new GetPlacesQuery[size]);
        }

    }
    ;

    protected GetPlacesQuery(Parcel in) {
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.placesList = ((PlacesList) in.readValue((PlacesList.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetPlacesQuery() {
    }

    /**
     * 
     * @param expirationDate Expiration date sent by the API
     * @param placesList List of places
     */
    public GetPlacesQuery(String expirationDate, PlacesList placesList) {
        super();
        this.expirationDate = expirationDate;
        this.placesList = placesList;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PlacesList getPlacesList() {
        return placesList;
    }

    public void setPlacesList(PlacesList placesList) {
        this.placesList = placesList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(expirationDate);
        dest.writeValue(placesList);
    }

    public int describeContents() {
        return  0;
    }

}
