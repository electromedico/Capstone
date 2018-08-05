
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StopArea implements Parcelable
{
    @SerializedName("cityId")
    @Expose
    private String cityId;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("uniqueStopId")
    @Expose
    private String uniqueStopId;
    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = new ArrayList<>();

    public final static Parcelable.Creator<StopArea> CREATOR = new Creator<StopArea>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StopArea createFromParcel(Parcel in) {
            return new StopArea(in);
        }

        public StopArea[] newArray(int size) {
            return (new StopArea[size]);
        }

    }
    ;

    protected StopArea(Parcel in) {
        this.cityId = ((String) in.readValue((String.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
        this.uniqueStopId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.schedules, (com.example.alex.capstone.model.Schedule.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public StopArea() {
    }

    /**
     * 
     * @param id
     * @param name
     * @param cityName
     * @param y
     * @param x
     */
    public StopArea(String cityName, String id, String name, String x, String y) {
        super();
        this.cityName = cityName;
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @param cityId
     * @param cityName
     * @param id
     * @param name
     */
    public StopArea(String cityId, String cityName, String id, String name) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @param id
     * @param schedules
     * @param cityId
     * @param name
     * @param cityName
     * @param uniqueStopId
     */
    public StopArea(String cityId, String cityName, String id, String name, List<Schedule> schedules, String uniqueStopId) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.id = id;
        this.name = name;
        this.schedules = schedules;
        this.uniqueStopId = uniqueStopId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUniqueStopId() {
        return uniqueStopId;
    }

    public void setUniqueStopId(String uniqueStopId) {
        this.uniqueStopId = uniqueStopId;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityId);
        dest.writeValue(cityName);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(x);
        dest.writeValue(y);
        dest.writeList(schedules);
        dest.writeValue(uniqueStopId);

    }

    public int describeContents() {
        return  0;
    }

}
