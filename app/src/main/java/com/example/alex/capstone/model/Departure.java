
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Departure implements Parcelable
{

    public String dateTime;
    public List<Destination> destination = null;
    public Line line;
    public String realTime;
    public final static Parcelable.Creator<Departure> CREATOR = new Creator<Departure>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Departure createFromParcel(Parcel in) {
            return new Departure(in);
        }

        public Departure[] newArray(int size) {
            return (new Departure[size]);
        }

    }
    ;

    protected Departure(Parcel in) {
        this.dateTime = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.destination, (com.example.alex.capstone.model.Destination.class.getClassLoader()));
        this.line = ((Line) in.readValue((Line.class.getClassLoader())));
        this.realTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Departure() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dateTime);
        dest.writeList(destination);
        dest.writeValue(line);
        dest.writeValue(realTime);
    }

    public int describeContents() {
        return  0;
    }

}
