
package com.example.alex.capstone.model.getJourneysQueryModel;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Journey_ implements Parcelable
{

    @SerializedName("arrivalDateTime")
    @Expose
    private String arrivalDateTime;
    @SerializedName("arrivalText")
    @Expose
    private ArrivalText arrivalText;
    @SerializedName("chunks")
    @Expose
    private List<Chunk> chunks = new ArrayList<>();
    @SerializedName("co2_emissions")
    @Expose
    private String co2Emissions;
    @SerializedName("departureDateTime")
    @Expose
    private String departureDateTime;
    @SerializedName("duration")
    @Expose
    private String duration;
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
        in.readList(this.chunks, (com.example.alex.capstone.model.getJourneysQueryModel.Chunk.class.getClassLoader()));
        this.co2Emissions = ((String) in.readValue((String.class.getClassLoader())));
        this.departureDateTime = ((String) in.readValue((String.class.getClassLoader())));
        this.duration = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Journey_() {
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public ArrivalText getArrivalText() {
        return arrivalText;
    }

    public void setArrivalText(ArrivalText arrivalText) {
        this.arrivalText = arrivalText;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(List<Chunk> chunks) {
        this.chunks = chunks;
    }

    public String getCo2Emissions() {
        return co2Emissions;
    }

    public void setCo2Emissions(String co2Emissions) {
        this.co2Emissions = co2Emissions;
    }

    public String getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
