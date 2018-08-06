
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journey implements Parcelable
{

    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("realTime")
    @Expose
    private String realTime;
    @SerializedName("waiting_time")
    @Expose
    private String waitingTime;
    public final static Parcelable.Creator<Journey> CREATOR = new Creator<Journey>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Journey createFromParcel(Parcel in) {
            return new Journey(in);
        }

        public Journey[] newArray(int size) {
            return (new Journey[size]);
        }

    }
    ;

    protected Journey(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.realTime = ((String) in.readValue((String.class.getClassLoader())));
        this.waitingTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Journey() {
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getRealTime() {
        return realTime;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTime);
        dest.writeValue(realTime);
        dest.writeValue(waitingTime);
    }

    public int describeContents() {
        return  0;
    }

}
