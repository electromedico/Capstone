
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Places implements Parcelable
{

    @SerializedName("arrivalCity")
    @Expose
    private String arrivalCity;
    @SerializedName("arrivalLatitude")
    @Expose
    private String arrivalLatitude;
    @SerializedName("arrivalLongitude")
    @Expose
    private String arrivalLongitude;
    @SerializedName("arrivalStop")
    @Expose
    private String arrivalStop;
    @SerializedName("departureCity")
    @Expose
    private String departureCity;
    @SerializedName("departureLatitude")
    @Expose
    private String departureLatitude;
    @SerializedName("departureLongitude")
    @Expose
    private String departureLongitude;
    @SerializedName("departureStop")
    @Expose
    private String departureStop;
    public final static Parcelable.Creator<Places> CREATOR = new Creator<Places>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        public Places[] newArray(int size) {
            return (new Places[size]);
        }

    }
    ;

    protected Places(Parcel in) {
        this.arrivalCity = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalStop = ((String) in.readValue((String.class.getClassLoader())));
        this.departureCity = ((String) in.readValue((String.class.getClassLoader())));
        this.departureLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.departureLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.departureStop = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Places() {
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalLatitude() {
        return arrivalLatitude;
    }

    public void setArrivalLatitude(String arrivalLatitude) {
        this.arrivalLatitude = arrivalLatitude;
    }

    public String getArrivalLongitude() {
        return arrivalLongitude;
    }

    public void setArrivalLongitude(String arrivalLongitude) {
        this.arrivalLongitude = arrivalLongitude;
    }

    public String getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(String arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureLatitude() {
        return departureLatitude;
    }

    public void setDepartureLatitude(String departureLatitude) {
        this.departureLatitude = departureLatitude;
    }

    public String getDepartureLongitude() {
        return departureLongitude;
    }

    public void setDepartureLongitude(String departureLongitude) {
        this.departureLongitude = departureLongitude;
    }

    public String getDepartureStop() {
        return departureStop;
    }

    public void setDepartureStop(String departureStop) {
        this.departureStop = departureStop;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalCity);
        dest.writeValue(arrivalLatitude);
        dest.writeValue(arrivalLongitude);
        dest.writeValue(arrivalStop);
        dest.writeValue(departureCity);
        dest.writeValue(departureLatitude);
        dest.writeValue(departureLongitude);
        dest.writeValue(departureStop);
    }

    public int describeContents() {
        return  0;
    }

}
