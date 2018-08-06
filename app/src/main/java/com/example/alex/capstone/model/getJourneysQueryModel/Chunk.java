
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chunk implements Parcelable
{

    @SerializedName("street")
    @Expose
    private Street street;
    @SerializedName("stop")
    @Expose
    private Stop stop;
    @SerializedName("service")
    @Expose
    private Service service;
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
        this.street = ((Street) in.readValue((Street.class.getClassLoader())));
        this.stop = ((Stop) in.readValue((Stop.class.getClassLoader())));
        this.service = ((Service) in.readValue((Service.class.getClassLoader())));
    }

    public Chunk() {
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(street);
        dest.writeValue(stop);
        dest.writeValue(service);
    }

    public int describeContents() {
        return  0;
    }

}
