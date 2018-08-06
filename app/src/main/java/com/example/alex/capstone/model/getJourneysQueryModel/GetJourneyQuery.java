
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetJourneyQuery implements Parcelable
{

    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("routePlannerResult")
    @Expose
    private RoutePlannerResult routePlannerResult;
    public final static Parcelable.Creator<GetJourneyQuery> CREATOR = new Creator<GetJourneyQuery>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetJourneyQuery createFromParcel(Parcel in) {
            return new GetJourneyQuery(in);
        }

        public GetJourneyQuery[] newArray(int size) {
            return (new GetJourneyQuery[size]);
        }

    }
    ;

    protected GetJourneyQuery(Parcel in) {
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.routePlannerResult = ((RoutePlannerResult) in.readValue((RoutePlannerResult.class.getClassLoader())));
    }

    public GetJourneyQuery() {
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public RoutePlannerResult getRoutePlannerResult() {
        return routePlannerResult;
    }

    public void setRoutePlannerResult(RoutePlannerResult routePlannerResult) {
        this.routePlannerResult = routePlannerResult;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(expirationDate);
        dest.writeValue(routePlannerResult);
    }

    public int describeContents() {
        return  0;
    }

}
