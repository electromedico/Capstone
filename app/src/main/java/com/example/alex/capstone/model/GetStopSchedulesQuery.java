
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStopSchedulesQuery implements Parcelable
{

    @SerializedName("departures")
    @Expose
    private Departures departures;
    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    public final static Parcelable.Creator<GetStopSchedulesQuery> CREATOR = new Creator<GetStopSchedulesQuery>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetStopSchedulesQuery createFromParcel(Parcel in) {
            return new GetStopSchedulesQuery(in);
        }

        public GetStopSchedulesQuery[] newArray(int size) {
            return (new GetStopSchedulesQuery[size]);
        }

    }
    ;

    protected GetStopSchedulesQuery(Parcel in) {
        this.departures = ((Departures) in.readValue((Departures.class.getClassLoader())));
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetStopSchedulesQuery() {
    }

    public Departures getDepartures() {
        return departures;
    }

    public void setDepartures(Departures departures) {
        this.departures = departures;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(departures);
        dest.writeValue(expirationDate);
    }

    public int describeContents() {
        return  0;
    }

}
