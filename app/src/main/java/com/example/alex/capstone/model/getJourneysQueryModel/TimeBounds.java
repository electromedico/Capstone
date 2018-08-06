
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeBounds implements Parcelable
{

    @SerializedName("maxDepartureHour")
    @Expose
    private String maxDepartureHour;
    @SerializedName("minArrivalHour")
    @Expose
    private String minArrivalHour;
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

    public String getMaxDepartureHour() {
        return maxDepartureHour;
    }

    public void setMaxDepartureHour(String maxDepartureHour) {
        this.maxDepartureHour = maxDepartureHour;
    }

    public String getMinArrivalHour() {
        return minArrivalHour;
    }

    public void setMinArrivalHour(String minArrivalHour) {
        this.minArrivalHour = minArrivalHour;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxDepartureHour);
        dest.writeValue(minArrivalHour);
    }

    public int describeContents() {
        return  0;
    }

}
