
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhysicalStops implements Parcelable
{

    @SerializedName("physicalStop")
    @Expose
    private List<PhysicalStop> physicalStop = new ArrayList<>();
    public final static Parcelable.Creator<PhysicalStops> CREATOR = new Creator<PhysicalStops>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PhysicalStops createFromParcel(Parcel in) {
            return new PhysicalStops(in);
        }

        public PhysicalStops[] newArray(int size) {
            return (new PhysicalStops[size]);
        }

    }
    ;

    protected PhysicalStops(Parcel in) {
        in.readList(this.physicalStop, (com.example.alex.capstone.model.PhysicalStop.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PhysicalStops() {
    }

    /**
     * 
     * @param physicalStop
     */
    public PhysicalStops(List<PhysicalStop> physicalStop) {
        super();
        this.physicalStop = physicalStop;
    }

    public List<PhysicalStop> getPhysicalStop() {
        return physicalStop;
    }

    public void setPhysicalStop(List<PhysicalStop> physicalStop) {
        this.physicalStop = physicalStop;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(physicalStop);
    }

    public int describeContents() {
        return  0;
    }

}
