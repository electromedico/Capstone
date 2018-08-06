
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartAddress implements Parcelable
{

    @SerializedName("connectionPlace")
    @Expose
    private ConnectionPlace connectionPlace;
    public final static Parcelable.Creator<StartAddress> CREATOR = new Creator<StartAddress>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StartAddress createFromParcel(Parcel in) {
            return new StartAddress(in);
        }

        public StartAddress[] newArray(int size) {
            return (new StartAddress[size]);
        }

    }
    ;

    protected StartAddress(Parcel in) {
        this.connectionPlace = ((ConnectionPlace) in.readValue((ConnectionPlace.class.getClassLoader())));
    }

    public StartAddress() {
    }

    public ConnectionPlace getConnectionPlace() {
        return connectionPlace;
    }

    public void setConnectionPlace(ConnectionPlace connectionPlace) {
        this.connectionPlace = connectionPlace;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(connectionPlace);
    }

    public int describeContents() {
        return  0;
    }

}
