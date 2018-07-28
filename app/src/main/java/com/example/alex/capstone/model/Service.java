
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Service implements Parcelable
{

    public DestinationStop destinationStop;
    public String duration;
    public String firstArrivalTime;
    public String firstDepartureTime;
    public String isContinuousService;
    public String lastArrivalTime;
    public String lastDepartureTime;
    public String maxWaitingTime;
    public String name;
    public Text_ text;
    public String wkt;
    public final static Parcelable.Creator<Service> CREATOR = new Creator<Service>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        public Service[] newArray(int size) {
            return (new Service[size]);
        }

    }
    ;

    protected Service(Parcel in) {
        this.destinationStop = ((DestinationStop) in.readValue((DestinationStop.class.getClassLoader())));
        this.duration = ((String) in.readValue((String.class.getClassLoader())));
        this.firstArrivalTime = ((String) in.readValue((String.class.getClassLoader())));
        this.firstDepartureTime = ((String) in.readValue((String.class.getClassLoader())));
        this.isContinuousService = ((String) in.readValue((String.class.getClassLoader())));
        this.lastArrivalTime = ((String) in.readValue((String.class.getClassLoader())));
        this.lastDepartureTime = ((String) in.readValue((String.class.getClassLoader())));
        this.maxWaitingTime = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((Text_) in.readValue((Text_.class.getClassLoader())));
        this.wkt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Service() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(destinationStop);
        dest.writeValue(duration);
        dest.writeValue(firstArrivalTime);
        dest.writeValue(firstDepartureTime);
        dest.writeValue(isContinuousService);
        dest.writeValue(lastArrivalTime);
        dest.writeValue(lastDepartureTime);
        dest.writeValue(maxWaitingTime);
        dest.writeValue(name);
        dest.writeValue(text);
        dest.writeValue(wkt);
    }

    public int describeContents() {
        return  0;
    }

}
