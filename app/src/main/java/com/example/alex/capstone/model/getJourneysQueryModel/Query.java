
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query implements Parcelable
{

    @SerializedName("maxSolutions")
    @Expose
    private String maxSolutions;
    @SerializedName("places")
    @Expose
    private Places places;
    @SerializedName("roadMode")
    @Expose
    private String roadMode;
    @SerializedName("timeBounds")
    @Expose
    private TimeBounds timeBounds;
    public final static Parcelable.Creator<Query> CREATOR = new Creator<Query>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Query createFromParcel(Parcel in) {
            return new Query(in);
        }

        public Query[] newArray(int size) {
            return (new Query[size]);
        }

    }
    ;

    protected Query(Parcel in) {
        this.maxSolutions = ((String) in.readValue((String.class.getClassLoader())));
        this.places = ((Places) in.readValue((Places.class.getClassLoader())));
        this.roadMode = ((String) in.readValue((String.class.getClassLoader())));
        this.timeBounds = ((TimeBounds) in.readValue((TimeBounds.class.getClassLoader())));
    }

    public Query() {
    }

    public String getMaxSolutions() {
        return maxSolutions;
    }

    public void setMaxSolutions(String maxSolutions) {
        this.maxSolutions = maxSolutions;
    }

    public Places getPlaces() {
        return places;
    }

    public void setPlaces(Places places) {
        this.places = places;
    }

    public String getRoadMode() {
        return roadMode;
    }

    public void setRoadMode(String roadMode) {
        this.roadMode = roadMode;
    }

    public TimeBounds getTimeBounds() {
        return timeBounds;
    }

    public void setTimeBounds(TimeBounds timeBounds) {
        this.timeBounds = timeBounds;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(maxSolutions);
        dest.writeValue(places);
        dest.writeValue(roadMode);
        dest.writeValue(timeBounds);
    }

    public int describeContents() {
        return  0;
    }

}
