
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PlacesList implements Parcelable
{

    public List<Place> place = null;
    public final static Parcelable.Creator<PlacesList> CREATOR = new Creator<PlacesList>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PlacesList createFromParcel(Parcel in) {
            return new PlacesList(in);
        }

        public PlacesList[] newArray(int size) {
            return (new PlacesList[size]);
        }

    }
    ;

    protected PlacesList(Parcel in) {
        in.readList(this.place, (com.example.alex.capstone.model.Place.class.getClassLoader()));
    }

    public PlacesList() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(place);
    }

    public int describeContents() {
        return  0;
    }

}
