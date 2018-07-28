
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Destination implements Parcelable
{

    public String cityName;
    public String id;
    public String name;
    public final static Parcelable.Creator<Destination> CREATOR = new Creator<Destination>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Destination createFromParcel(Parcel in) {
            return new Destination(in);
        }

        public Destination[] newArray(int size) {
            return (new Destination[size]);
        }

    }
    ;

    protected Destination(Parcel in) {
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Destination() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityName);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
