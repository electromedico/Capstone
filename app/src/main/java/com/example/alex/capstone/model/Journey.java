
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Journey implements Parcelable
{

    public Journey_ journey;
    public final static Parcelable.Creator<Journey> CREATOR = new Creator<Journey>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Journey createFromParcel(Parcel in) {
            return new Journey(in);
        }

        public Journey[] newArray(int size) {
            return (new Journey[size]);
        }

    }
    ;

    protected Journey(Parcel in) {
        this.journey = ((Journey_) in.readValue((Journey_.class.getClassLoader())));
    }

    public Journey() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(journey);
    }

    public int describeContents() {
        return  0;
    }

}
