
package com.example.alex.capstone.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RoutePlannerResult implements Parcelable
{

    public List<Journey> journeys = null;
    public Query query;
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
        in.readList(this.journeys, (com.example.alex.capstone.model.Journey.class.getClassLoader()));
        this.query = ((Query) in.readValue((Query.class.getClassLoader())));
    }

    public RoutePlannerResult() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(journeys);
        dest.writeValue(query);
    }

    public int describeContents() {
        return  0;
    }

}
