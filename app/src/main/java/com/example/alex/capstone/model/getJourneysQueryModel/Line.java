
package com.example.alex.capstone.model.getJourneysQueryModel;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line implements Parcelable
{

    @SerializedName("bgXmlColor")
    @Expose
    private String bgXmlColor;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("fgXmlColor")
    @Expose
    private String fgXmlColor;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private List<Message> message = new ArrayList<Message>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("service_number")
    @Expose
    private String serviceNumber;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("style")
    @Expose
    private String style;
    @SerializedName("transportMode")
    @Expose
    private TransportMode transportMode;
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
        in.readList(this.message, (com.example.alex.capstone.model.getJourneysQueryModel.Message.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.network = ((String) in.readValue((String.class.getClassLoader())));
        this.serviceNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.shortName = ((String) in.readValue((String.class.getClassLoader())));
        this.style = ((String) in.readValue((String.class.getClassLoader())));
        this.transportMode = ((TransportMode) in.readValue((TransportMode.class.getClassLoader())));
    }

    public Line() {
    }

    public String getBgXmlColor() {
        return bgXmlColor;
    }

    public void setBgXmlColor(String bgXmlColor) {
        this.bgXmlColor = bgXmlColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFgXmlColor() {
        return fgXmlColor;
    }

    public void setFgXmlColor(String fgXmlColor) {
        this.fgXmlColor = fgXmlColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
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
