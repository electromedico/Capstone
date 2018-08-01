package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GetStopPointsQuery implements Parcelable
{

    private String expirationDate;
    private PhysicalStops physicalStops;
    public final static Parcelable.Creator<GetStopPointsQuery> CREATOR = new Creator<GetStopPointsQuery>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetStopPointsQuery createFromParcel(Parcel in) {
            return new GetStopPointsQuery(in);
        }

        public GetStopPointsQuery[] newArray(int size) {
            return (new GetStopPointsQuery[size]);
        }

    }
            ;

    protected GetStopPointsQuery(Parcel in) {
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.physicalStops = ((PhysicalStops) in.readValue((PhysicalStops.class.getClassLoader())));
    }

    public GetStopPointsQuery() {
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PhysicalStops getPhysicalStops() {
        return physicalStops;
    }

    public void setPhysicalStops(PhysicalStops physicalStops) {
        this.physicalStops = physicalStops;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(expirationDate);
        dest.writeValue(physicalStops);
    }

    public int describeContents() {
        return 0;
    }

}