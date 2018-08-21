
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStopPointsQuery implements Parcelable
{

    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("physicalStops")
    @Expose
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public GetStopPointsQuery() {
    }

    /**
     * Constructor
     * @param expirationDate Expiration date sent by the API
     * @param physicalStops List of Physical Stops
     */
    public GetStopPointsQuery(String expirationDate, PhysicalStops physicalStops) {
        super();
        this.expirationDate = expirationDate;
        this.physicalStops = physicalStops;
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
        return  0;
    }

}
