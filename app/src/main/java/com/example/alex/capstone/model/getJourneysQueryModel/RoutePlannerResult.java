
package com.example.alex.capstone.model.getJourneysQueryModel;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoutePlannerResult implements Parcelable
{

    @SerializedName("journeys")
    @Expose
    private List<Journey> journeys = new ArrayList<>();
    @SerializedName("query")
    @Expose
    private Query query;
    public final static Parcelable.Creator<RoutePlannerResult> CREATOR = new Creator<RoutePlannerResult>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RoutePlannerResult createFromParcel(Parcel in) {
            return new RoutePlannerResult(in);
        }

        public RoutePlannerResult[] newArray(int size) {
            return (new RoutePlannerResult[size]);
        }

    }
    ;

    protected RoutePlannerResult(Parcel in) {
        in.readList(this.journeys, (com.example.alex.capstone.model.getJourneysQueryModel.Journey.class.getClassLoader()));
        this.query = ((Query) in.readValue((Query.class.getClassLoader())));
    }

    public RoutePlannerResult() {
    }

    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(journeys);
        dest.writeValue(query);
    }

    public int describeContents() {
        return  0;
    }

}
