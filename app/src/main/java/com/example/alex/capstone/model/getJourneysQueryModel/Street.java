
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Street implements Parcelable
{

    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("endAddress")
    @Expose
    private EndAddress endAddress;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("roadMode")
    @Expose
    private String roadMode;
    @SerializedName("startAddress")
    @Expose
    private StartAddress startAddress;
    @SerializedName("text")
    @Expose
    private Text text;
    @SerializedName("wkt")
    @Expose
    private String wkt;
    public final static Parcelable.Creator<Street> CREATOR = new Creator<Street>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        public Street[] newArray(int size) {
            return (new Street[size]);
        }

    }
    ;

    protected Street(Parcel in) {
        this.arrivalTime = ((String) in.readValue((String.class.getClassLoader())));
        this.departureTime = ((String) in.readValue((String.class.getClassLoader())));
        this.duration = ((String) in.readValue((String.class.getClassLoader())));
        this.endAddress = ((EndAddress) in.readValue((EndAddress.class.getClassLoader())));
        this.length = ((String) in.readValue((String.class.getClassLoader())));
        this.roadMode = ((String) in.readValue((String.class.getClassLoader())));
        this.startAddress = ((StartAddress) in.readValue((StartAddress.class.getClassLoader())));
        this.text = ((Text) in.readValue((Text.class.getClassLoader())));
        this.wkt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Street() {
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public EndAddress getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(EndAddress endAddress) {
        this.endAddress = endAddress;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRoadMode() {
        return roadMode;
    }

    public void setRoadMode(String roadMode) {
        this.roadMode = roadMode;
    }

    public StartAddress getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(StartAddress startAddress) {
        this.startAddress = startAddress;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalTime);
        dest.writeValue(departureTime);
        dest.writeValue(duration);
        dest.writeValue(endAddress);
        dest.writeValue(length);
        dest.writeValue(roadMode);
        dest.writeValue(startAddress);
        dest.writeValue(text);
        dest.writeValue(wkt);
    }

    public int describeContents() {
        return  0;
    }

}
