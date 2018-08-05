
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departure implements Parcelable
{

    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("destination")
    @Expose
    private List<Destination> destination = new ArrayList<>();
    @SerializedName("line")
    @Expose
    private Line line;
    @SerializedName("realTime")
    @Expose
    private String realTime;
    public final static Parcelable.Creator<Departure> CREATOR = new Creator<Departure>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Departure createFromParcel(Parcel in) {
            return new Departure(in);
        }

        public Departure[] newArray(int size) {
            return (new Departure[size]);
        }

    }
    ;

    protected Departure(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.destination, (com.example.alex.capstone.model.Destination.class.getClassLoader()));
        this.line = ((Line) in.readValue((Line.class.getClassLoader())));
        this.realTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Departure() {
    }

    /**
     * 
     * @param realTime
     * @param dateTime
     * @param line
     * @param destination
     */
    public Departure(String dateTime, List<Destination> destination, Line line, String realTime) {
        super();
        this.dateTime = dateTime;
        this.destination = destination;
        this.line = line;
        this.realTime = realTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public List<Destination> getDestination() {
        return destination;
    }

    public void setDestination(List<Destination> destination) {
        this.destination = destination;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTime);
        dest.writeList(destination);
        dest.writeValue(line);
        dest.writeValue(realTime);
    }

    public int describeContents() {
        return  0;
    }

}
