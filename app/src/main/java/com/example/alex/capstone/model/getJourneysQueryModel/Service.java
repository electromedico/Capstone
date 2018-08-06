
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service implements Parcelable
{

    @SerializedName("destinationStop")
    @Expose
    private DestinationStop destinationStop;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("firstArrivalTime")
    @Expose
    private String firstArrivalTime;
    @SerializedName("firstDepartureTime")
    @Expose
    private String firstDepartureTime;
    @SerializedName("isContinuousService")
    @Expose
    private String isContinuousService;
    @SerializedName("lastArrivalTime")
    @Expose
    private String lastArrivalTime;
    @SerializedName("lastDepartureTime")
    @Expose
    private String lastDepartureTime;
    @SerializedName("maxWaitingTime")
    @Expose
    private String maxWaitingTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("text")
    @Expose
    private Text__ text;
    @SerializedName("wkt")
    @Expose
    private String wkt;
    public final static Parcelable.Creator<Service> CREATOR = new Creator<Service>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return (new Service[size]);
        }

    }
    ;

    protected Service(Parcel in) {
        this.destinationStop = ((DestinationStop) in.readValue((DestinationStop.class.getClassLoader())));
        this.duration = ((String) in.readValue((String.class.getClassLoader())));
        this.firstArrivalTime = ((String) in.readValue((String.class.getClassLoader())));
        this.firstDepartureTime = ((String) in.readValue((String.class.getClassLoader())));
        this.isContinuousService = ((String) in.readValue((String.class.getClassLoader())));
        this.lastArrivalTime = ((String) in.readValue((String.class.getClassLoader())));
        this.lastDepartureTime = ((String) in.readValue((String.class.getClassLoader())));
        this.maxWaitingTime = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((Text__) in.readValue((Text__.class.getClassLoader())));
        this.wkt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Service() {
    }

    public DestinationStop getDestinationStop() {
        return destinationStop;
    }

    public void setDestinationStop(DestinationStop destinationStop) {
        this.destinationStop = destinationStop;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFirstArrivalTime() {
        return firstArrivalTime;
    }

    public void setFirstArrivalTime(String firstArrivalTime) {
        this.firstArrivalTime = firstArrivalTime;
    }

    public String getFirstDepartureTime() {
        return firstDepartureTime;
    }

    public void setFirstDepartureTime(String firstDepartureTime) {
        this.firstDepartureTime = firstDepartureTime;
    }

    public String getIsContinuousService() {
        return isContinuousService;
    }

    public void setIsContinuousService(String isContinuousService) {
        this.isContinuousService = isContinuousService;
    }

    public String getLastArrivalTime() {
        return lastArrivalTime;
    }

    public void setLastArrivalTime(String lastArrivalTime) {
        this.lastArrivalTime = lastArrivalTime;
    }

    public String getLastDepartureTime() {
        return lastDepartureTime;
    }

    public void setLastDepartureTime(String lastDepartureTime) {
        this.lastDepartureTime = lastDepartureTime;
    }

    public String getMaxWaitingTime() {
        return maxWaitingTime;
    }

    public void setMaxWaitingTime(String maxWaitingTime) {
        this.maxWaitingTime = maxWaitingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Text__ getText() {
        return text;
    }

    public void setText(Text__ text) {
        this.text = text;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(destinationStop);
        dest.writeValue(duration);
        dest.writeValue(firstArrivalTime);
        dest.writeValue(firstDepartureTime);
        dest.writeValue(isContinuousService);
        dest.writeValue(lastArrivalTime);
        dest.writeValue(lastDepartureTime);
        dest.writeValue(maxWaitingTime);
        dest.writeValue(name);
        dest.writeValue(text);
        dest.writeValue(wkt);
    }

    public int describeContents() {
        return  0;
    }

}
