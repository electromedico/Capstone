
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TimeBounds implements Parcelable
{

    public String maxDepartureHour;
    public String minArrivalHour;
    public final static Parcelable.Creator<TimeBounds> CREATOR = new Creator<TimeBounds>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TimeBounds createFromParcel(Parcel in) {
            return new TimeBounds(in);
        }

        public TimeBounds[] newArray(int size) {
            return (new TimeBounds[size]);
        }

    }
    ;

    protected TimeBounds(Parcel in) {
        this.maxDepartureHour = ((String) in.readValue((String.class.getClassLoader())));
        this.minArrivalHour = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TimeBounds() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxDepartureHour);
        dest.writeValue(minArrivalHour);
    }

    public int describeContents() {
        return  0;
    }

}
