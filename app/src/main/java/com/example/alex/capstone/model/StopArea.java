
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StopArea implements Parcelable
{

    public String cityName;
    public String id;
    public String name;
    public String x;
    public String y;
    public final static Parcelable.Creator<StopArea> CREATOR = new Creator<StopArea>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StopArea createFromParcel(Parcel in) {
            return new StopArea(in);
        }

        public StopArea[] newArray(int size) {
            return (new StopArea[size]);
        }

    }
    ;

    protected StopArea(Parcel in) {
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StopArea() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityName);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(x);
        dest.writeValue(y);
    }

    public int describeContents() {
        return  0;
    }

}
