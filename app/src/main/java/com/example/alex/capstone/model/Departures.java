
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departures implements Parcelable
{

    @SerializedName("stopAreas")
    @Expose
    private List<StopArea> stopAreas = new ArrayList<>();
    public final static Parcelable.Creator<Departures> CREATOR = new Creator<Departures>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Departures createFromParcel(Parcel in) {
            return new Departures(in);
        }

        public Departures[] newArray(int size) {
            return (new Departures[size]);
        }

    }
    ;

    protected Departures(Parcel in) {
        in.readList(this.stopAreas, (com.example.alex.capstone.model.StopArea.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Departures() {
    }

    /**
     * 
     * @param stopAreas
     */
    public Departures(List<StopArea> stopAreas) {
        super();
        this.stopAreas = stopAreas;
    }

    public List<StopArea> getStopAreas() {
        return stopAreas;
    }

    public void setStopAreas(List<StopArea> stopAreas) {
        this.stopAreas = stopAreas;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(stopAreas);
    }

    public int describeContents() {
        return  0;
    }

}
