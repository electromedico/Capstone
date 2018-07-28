
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Places implements Parcelable
{

    public String arrivalCity;
    public String arrivalLatitude;
    public String arrivalLongitude;
    public String arrivalStop;
    public String departureCity;
    public String departureLatitude;
    public String departureLongitude;
    public String departureStop;
    public final static Parcelable.Creator<Places> CREATOR = new Creator<Places>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Places createFromParcel(Parcel in) {
            return new Places(in);
        }

        public Places[] newArray(int size) {
            return (new Places[size]);
        }

    }
    ;

    protected Places(Parcel in) {
        this.arrivalCity = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalStop = ((String) in.readValue((String.class.getClassLoader())));
        this.departureCity = ((String) in.readValue((String.class.getClassLoader())));
        this.departureLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.departureLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.departureStop = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Places() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalCity);
        dest.writeValue(arrivalLatitude);
        dest.writeValue(arrivalLongitude);
        dest.writeValue(arrivalStop);
        dest.writeValue(departureCity);
        dest.writeValue(departureLatitude);
        dest.writeValue(departureLongitude);
        dest.writeValue(departureStop);
    }

    public int describeContents() {
        return  0;
    }

}
