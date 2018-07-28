
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Stoppoint implements Parcelable
{

    public String arrivalId;
    public ConnectionPlace connectionPlace;
    public String departureId;
    public String firstTime;
    public String lastTime;
    public String latitude;
    public String longitude;
    public String name;
    public Text text;
    public final static Parcelable.Creator<Stoppoint> CREATOR = new Creator<Stoppoint>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Stoppoint createFromParcel(Parcel in) {
            return new Stoppoint(in);
        }

        public Stoppoint[] newArray(int size) {
            return (new Stoppoint[size]);
        }

    }
    ;

    protected Stoppoint(Parcel in) {
        this.arrivalId = ((String) in.readValue((String.class.getClassLoader())));
        this.connectionPlace = ((ConnectionPlace) in.readValue((ConnectionPlace.class.getClassLoader())));
        this.departureId = ((String) in.readValue((String.class.getClassLoader())));
        this.firstTime = ((String) in.readValue((String.class.getClassLoader())));
        this.lastTime = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((Text) in.readValue((Text.class.getClassLoader())));
    }

    public Stoppoint() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalId);
        dest.writeValue(connectionPlace);
        dest.writeValue(departureId);
        dest.writeValue(firstTime);
        dest.writeValue(lastTime);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(name);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}
