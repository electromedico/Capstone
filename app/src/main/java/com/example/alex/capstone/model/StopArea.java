
package com.example.alex.capstone.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = new ArrayList<>();
    @SerializedName("uniqueStopId")
    @Expose
    private String uniqueStopId;
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
        in.readList(this.schedules, (com.example.alex.capstone.model.Schedule.class.getClassLoader()));
        this.uniqueStopId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StopArea() {
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public String getUniqueStopId() {
        return uniqueStopId;
    }

    public void setUniqueStopId(String uniqueStopId) {
        this.uniqueStopId = uniqueStopId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityId);
        dest.writeValue(cityName);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeList(schedules);
        dest.writeValue(uniqueStopId);
    }

    public int describeContents() {
        return  0;
    }

}
