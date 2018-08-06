
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestinationStop implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("line")
    @Expose
    private Line line;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<DestinationStop> CREATOR = new Creator<DestinationStop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DestinationStop createFromParcel(Parcel in) {
            return new DestinationStop(in);
        }

        public DestinationStop[] newArray(int size) {
            return (new DestinationStop[size]);
        }

    }
    ;

    protected DestinationStop(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.line = ((Line) in.readValue((Line.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DestinationStop() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(line);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
