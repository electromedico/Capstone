
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stop implements Parcelable
{

    @SerializedName("arrival_id")
    @Expose
    private String arrivalId;
    @SerializedName("connectionPlace")
    @Expose
    private ConnectionPlace_ connectionPlace;
    @SerializedName("departure_id")
    @Expose
    private String departureId;
    @SerializedName("firstTime")
    @Expose
    private String firstTime;
    @SerializedName("lastTime")
    @Expose
    private String lastTime;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private Text_ text;
    public final static Parcelable.Creator<Stop> CREATOR = new Creator<Stop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        public Stop[] newArray(int size) {
            return (new Stop[size]);
        }

    }
    ;

    protected Stop(Parcel in) {
        this.arrivalId = ((String) in.readValue((String.class.getClassLoader())));
        this.connectionPlace = ((ConnectionPlace_) in.readValue((ConnectionPlace_.class.getClassLoader())));
        this.departureId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstTime = ((String) in.readValue((String.class.getClassLoader())));
        this.lastTime = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((Text_) in.readValue((Text_.class.getClassLoader())));
    }

    public Stop() {
    }

    public String getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(String arrivalId) {
        this.arrivalId = arrivalId;
    }

    public ConnectionPlace_ getConnectionPlace() {
        return connectionPlace;
    }

    public void setConnectionPlace(ConnectionPlace_ connectionPlace) {
        this.connectionPlace = connectionPlace;
    }

    public String getDepartureId() {
        return departureId;
    }

    public void setDepartureId(String departureId) {
        this.departureId = departureId;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Text_ getText() {
        return text;
    }

    public void setText(Text_ text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalId);
        dest.writeValue(connectionPlace);
        dest.writeValue(departureId);
        dest.writeValue(firstTime);
        dest.writeValue(lastTime);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(name);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}
