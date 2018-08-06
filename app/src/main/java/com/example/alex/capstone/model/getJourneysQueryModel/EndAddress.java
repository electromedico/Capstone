
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EndAddress implements Parcelable
{

    @SerializedName("address")
    @Expose
    private Address address;
    public final static Parcelable.Creator<EndAddress> CREATOR = new Creator<EndAddress>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EndAddress createFromParcel(Parcel in) {
            return new EndAddress(in);
        }

        public EndAddress[] newArray(int size) {
            return (new EndAddress[size]);
        }

    }
    ;

    protected EndAddress(Parcel in) {
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
    }

    public EndAddress() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address);
    }

    public int describeContents() {
        return  0;
    }

}
