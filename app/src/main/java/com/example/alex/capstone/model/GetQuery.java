
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GetQuery implements Parcelable
{

    public String expirationDate;
    public Object object;
    public final static Parcelable.Creator<GetQuery> CREATOR = new Creator<GetQuery>() {


        @SuppressWarnings({
            "unchecked"
        })
        public GetQuery createFromParcel(Parcel in) {
            return new GetQuery(in);
        }

        public GetQuery[] newArray(int size) {
            return (new GetQuery[size]);
        }

    }
    ;

    protected GetQuery(Parcel in) {
        this.expirationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.object = in.readValue((Object.class.getClassLoader()));
    }

    public GetQuery() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(expirationDate);
        dest.writeValue(object);
    }

    public int describeContents() {
        return  0;
    }

}
