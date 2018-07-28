
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Query implements Parcelable
{

    public String maxSolutions;
    public Places places;
    public String roadMode;
    public TimeBounds timeBounds;
    public final static Parcelable.Creator<Query> CREATOR = new Creator<Query>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        public Query[] newArray(int size) {
            return (new Query[size]);
        }

    }
    ;

    protected Query(Parcel in) {
        this.maxSolutions = ((String) in.readValue((String.class.getClassLoader())));
        this.places = ((Places) in.readValue((Places.class.getClassLoader())));
        this.roadMode = ((String) in.readValue((String.class.getClassLoader())));
        this.timeBounds = ((TimeBounds) in.readValue((TimeBounds.class.getClassLoader())));
    }

    public Query() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxSolutions);
        dest.writeValue(places);
        dest.writeValue(roadMode);
        dest.writeValue(timeBounds);
    }

    public int describeContents() {
        return  0;
    }

}
