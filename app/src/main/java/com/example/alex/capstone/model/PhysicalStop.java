
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PhysicalStop implements Parcelable
{

    public List<Destination> destinations = null;
    public String handicappedCompliance;
    public String id;
    public List<Line> lines = null;
    public String name;
    public List<OperatorCode> operatorCodes = null;
    public StopArea stopArea;
    public String x;
    public String y;
    public final static Parcelable.Creator<PhysicalStop> CREATOR = new Creator<PhysicalStop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PhysicalStop createFromParcel(Parcel in) {
            return new PhysicalStop(in);
        }

        public PhysicalStop[] newArray(int size) {
            return (new PhysicalStop[size]);
        }

    }
    ;

    protected PhysicalStop(Parcel in) {
        in.readList(this.destinations, (com.example.alex.capstone.model.Destination.class.getClassLoader()));
        this.handicappedCompliance = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.lines, (com.example.alex.capstone.model.Line.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.operatorCodes, (com.example.alex.capstone.model.OperatorCode.class.getClassLoader()));
        this.stopArea = ((StopArea) in.readValue((StopArea.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PhysicalStop() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(destinations);
        dest.writeValue(handicappedCompliance);
        dest.writeValue(id);
        dest.writeList(lines);
        dest.writeValue(name);
        dest.writeList(operatorCodes);
        dest.writeValue(stopArea);
        dest.writeValue(x);
        dest.writeValue(y);
    }

    public int describeContents() {
        return  0;
    }

}
