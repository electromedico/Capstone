
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journey implements Parcelable
{

    @SerializedName("journey")
    @Expose
    private Journey_ journey;
    public final static Parcelable.Creator<Journey> CREATOR = new Creator<Journey>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Journey createFromParcel(Parcel in) {
            return new Journey(in);
        }

        public Journey[] newArray(int size) {
            return (new Journey[size]);
        }

    }
    ;

    protected Journey(Parcel in) {
        this.journey = ((Journey_) in.readValue((Journey_.class.getClassLoader())));
    }

    public Journey() {
    }

    public Journey_ getJourney() {
        return journey;
    }

    public void setJourney(Journey_ journey) {
        this.journey = journey;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(journey);
    }

    public int describeContents() {
        return  0;
    }

}
