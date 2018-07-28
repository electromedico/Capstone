
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Stop implements Parcelable
{

    public String id;
    public String name;
    public String operatorCode;
    public final static Parcelable.Creator<Stop> CREATOR = new Creator<Stop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        public Stop[] newArray(int size) {
            return (new Stop[size]);
        }

    }
    ;

    protected Stop(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.operatorCode = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Stop() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(operatorCode);
    }

    public int describeContents() {
        return  0;
    }

}
