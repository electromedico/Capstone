
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhysicalStop implements Parcelable
{

    @SerializedName("destinations")
    @Expose
    private List<Destination> destinations = new ArrayList<>();
    @SerializedName("handicappedCompliance")
    @Expose
    private String handicappedCompliance;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lines")
    @Expose
    private List<Line> lines = new ArrayList<>();
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("operatorCodes")
    @Expose
    private List<OperatorCode> operatorCodes = new ArrayList<>();
    @SerializedName("stopArea")
    @Expose
    private StopArea stopArea;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    public final static Parcelable.Creator<PhysicalStop> CREATOR = new Creator<PhysicalStop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PhysicalStop createFromParcel(Parcel in) {
            return new PhysicalStop(in);
        }

        public PhysicalStop[] newArray(int size) {
            return (new PhysicalStop[size]);
        }

    }
    ;

    protected PhysicalStop(Parcel in) {
        in.readList(this.destinations, (com.example.alex.capstone.model.Destination.class.getClassLoader()));
        this.handicappedCompliance = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.lines, (com.example.alex.capstone.model.Line.class.getClassLoader()));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.operatorCodes, (com.example.alex.capstone.model.OperatorCode.class.getClassLoader()));
        this.stopArea = ((StopArea) in.readValue((StopArea.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public PhysicalStop() {
    }

    /**
     * Constructor
     * @param id id
     * @param handicappedCompliance handicappedCompliance
     * @param destinations destinations
     * @param operatorCodes operatorCodes
     * @param stopArea stopArea
     * @param name name
     * @param lines lines
     * @param y y
     * @param x x
     */
    public PhysicalStop(List<Destination> destinations, String handicappedCompliance, String id, List<Line> lines, String name, List<OperatorCode> operatorCodes, StopArea stopArea, String x, String y) {
        super();
        this.destinations = destinations;
        this.handicappedCompliance = handicappedCompliance;
        this.id = id;
        this.lines = lines;
        this.name = name;
        this.operatorCodes = operatorCodes;
        this.stopArea = stopArea;
        this.x = x;
        this.y = y;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public String getHandicappedCompliance() {
        return handicappedCompliance;
    }

    public void setHandicappedCompliance(String handicappedCompliance) {
        this.handicappedCompliance = handicappedCompliance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OperatorCode> getOperatorCodes() {
        return operatorCodes;
    }

    public void setOperatorCodes(List<OperatorCode> operatorCodes) {
        this.operatorCodes = operatorCodes;
    }

    public StopArea getStopArea() {
        return stopArea;
    }

    public void setStopArea(StopArea stopArea) {
        this.stopArea = stopArea;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(destinations);
        dest.writeValue(handicappedCompliance);
        dest.writeValue(id);
        dest.writeList(lines);
        dest.writeValue(name);
        dest.writeList(operatorCodes);
        dest.writeValue(stopArea);
        dest.writeValue(x);
        dest.writeValue(y);
    }

    public int describeContents() {
        return  0;
    }

}
