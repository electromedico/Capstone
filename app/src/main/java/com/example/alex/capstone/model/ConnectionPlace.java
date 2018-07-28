
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ConnectionPlace implements Parcelable
{

    public String city;
    public String id;
    public String latitude;
    public String longitude;
    public String name;
    public String x;
    public String y;
    public final static Parcelable.Creator<ConnectionPlace> CREATOR = new Creator<ConnectionPlace>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ConnectionPlace createFromParcel(Parcel in) {
            return new ConnectionPlace(in);
        }

        public ConnectionPlace[] newArray(int size) {
            return (new ConnectionPlace[size]);
        }

    }
    ;

    protected ConnectionPlace(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ConnectionPlace() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(id);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(name);
        dest.writeValue(x);
        dest.writeValue(y);
    }

    public int describeContents() {
        return  0;
    }

}
