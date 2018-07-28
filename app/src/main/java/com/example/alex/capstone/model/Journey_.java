
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Journey_ implements Parcelable
{

    public String arrivalDateTime;
    public ArrivalText arrivalText;
    public List<Chunk> chunks = null;
    public String co2Emissions;
    public String departureDateTime;
    public String duration;
    public final static Parcelable.Creator<Journey_> CREATOR = new Creator<Journey_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Journey_ createFromParcel(Parcel in) {
            return new Journey_(in);
        }

        public Journey_[] newArray(int size) {
            return (new Journey_[size]);
        }

    }
    ;

    protected Journey_(Parcel in) {
        this.arrivalDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.arrivalText = ((ArrivalText) in.readValue((ArrivalText.class.getClassLoader())));
        in.readList(this.chunks, (com.example.alex.capstone.model.Chunk.class.getClassLoader()));
        this.co2Emissions = ((String) in.readValue((String.class.getClassLoader())));
        this.departureDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.duration = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Journey_() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(arrivalDateTime);
        dest.writeValue(arrivalText);
        dest.writeList(chunks);
        dest.writeValue(co2Emissions);
        dest.writeValue(departureDateTime);
        dest.writeValue(duration);
    }

    public int describeContents() {
        return  0;
    }

}
