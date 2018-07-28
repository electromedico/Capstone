package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PhysicalStops implements Parcelable
{

    private List<PhysicalStop> physicalStop = null;
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

    public PhysicalStops() {
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
        return 0;
    }

}