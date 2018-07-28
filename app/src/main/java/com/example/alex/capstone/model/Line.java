
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Line implements Parcelable
{

    public String bgXmlColor;
    public String color;
    public String fgXmlColor;
    public String id;
    public List<Message> message = null;
    public String name;
    public String network;
    public String serviceNumber;
    public String shortName;
    public String style;
    public TransportMode transportMode;
    public final static Parcelable.Creator<Line> CREATOR = new Creator<Line>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        public Line[] newArray(int size) {
            return (new Line[size]);
        }

    }
    ;

    protected Line(Parcel in) {
        this.bgXmlColor = ((String) in.readValue((String.class.getClassLoader())));
        this.color = ((String) in.readValue((String.class.getClassLoader())));
        this.fgXmlColor = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.message, (com.example.alex.capstone.model.Message.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.network = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.shortName = ((String) in.readValue((String.class.getClassLoader())));
        this.style = ((String) in.readValue((String.class.getClassLoader())));
        this.transportMode = ((TransportMode) in.readValue((TransportMode.class.getClassLoader())));
    }

    public Line() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(bgXmlColor);
        dest.writeValue(color);
        dest.writeValue(fgXmlColor);
        dest.writeValue(id);
        dest.writeList(message);
        dest.writeValue(name);
        dest.writeValue(network);
        dest.writeValue(serviceNumber);
        dest.writeValue(shortName);
        dest.writeValue(style);
        dest.writeValue(transportMode);
    }

    public int describeContents() {
        return  0;
    }

}
