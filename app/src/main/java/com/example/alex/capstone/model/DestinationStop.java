
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class DestinationStop implements Parcelable
{

    public String id;
    public Line line;
    public String name;
    public final static Parcelable.Creator<DestinationStop> CREATOR = new Creator<DestinationStop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DestinationStop createFromParcel(Parcel in) {
            return new DestinationStop(in);
        }

        public DestinationStop[] newArray(int size) {
            return (new DestinationStop[size]);
        }

    }
    ;

    protected DestinationStop(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.line = ((Line) in.readValue((Line.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DestinationStop() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(line);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
