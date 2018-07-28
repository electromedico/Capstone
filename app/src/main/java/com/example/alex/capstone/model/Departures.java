
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Departures implements Parcelable
{

    public List<Departure> departure = null;
    public Stop stop;
    public StopArea stopArea;
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
        in.readList(this.departure, (com.example.alex.capstone.model.Departure.class.getClassLoader()));
        this.stop = ((Stop) in.readValue((Stop.class.getClassLoader())));
        this.stopArea = ((StopArea) in.readValue((StopArea.class.getClassLoader())));
    }

    public Departures() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(departure);
        dest.writeValue(stop);
        dest.writeValue(stopArea);
    }

    public int describeContents() {
        return  0;
    }

}
