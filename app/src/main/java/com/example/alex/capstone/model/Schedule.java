
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule implements Parcelable
{

    @SerializedName("destination")
    @Expose
    private Destination destination;
    @SerializedName("journeys")
    @Expose
    private List<Journey> journeys = new ArrayList<>();
    @SerializedName("line")
    @Expose
    private Line line;
    @SerializedName("stop")
    @Expose
    private Stop stop;
    public final static Parcelable.Creator<Schedule> CREATOR = new Creator<Schedule>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        public Schedule[] newArray(int size) {
            return (new Schedule[size]);
        }

    }
    ;

    protected Schedule(Parcel in) {
        this.destination = ((Destination) in.readValue((Destination.class.getClassLoader())));
        in.readList(this.journeys, (com.example.alex.capstone.model.Journey.class.getClassLoader()));
        this.line = ((Line) in.readValue((Line.class.getClassLoader())));
        this.stop = ((Stop) in.readValue((Stop.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Schedule() {
    }

    /**
     * 
     * @param journeys
     * @param stop
     * @param line
     * @param destination
     */
    public Schedule(Destination destination, List<Journey> journeys, Line line, Stop stop) {
        super();
        this.destination = destination;
        this.journeys = journeys;
        this.line = line;
        this.stop = stop;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(destination);
        dest.writeList(journeys);
        dest.writeValue(line);
        dest.writeValue(stop);
    }

    public int describeContents() {
        return  0;
    }

}
