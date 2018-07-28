
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Chunk implements Parcelable
{

    public Stoppoint stoppoint;
    public Service service;
    public final static Parcelable.Creator<Chunk> CREATOR = new Creator<Chunk>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Chunk createFromParcel(Parcel in) {
            return new Chunk(in);
        }

        public Chunk[] newArray(int size) {
            return (new Chunk[size]);
        }

    }
    ;

    protected Chunk(Parcel in) {
        this.stoppoint = ((Stoppoint) in.readValue((Stoppoint.class.getClassLoader())));
        this.service = ((Service) in.readValue((Service.class.getClassLoader())));
    }

    public Chunk() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(stoppoint);
        dest.writeValue(service);
    }

    public int describeContents() {
        return  0;
    }

}
