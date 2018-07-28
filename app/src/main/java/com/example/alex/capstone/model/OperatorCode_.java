
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OperatorCode_ implements Parcelable
{

    public String network;
    public String value;
    public final static Parcelable.Creator<OperatorCode_> CREATOR = new Creator<OperatorCode_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OperatorCode_ createFromParcel(Parcel in) {
            return new OperatorCode_(in);
        }

        public OperatorCode_[] newArray(int size) {
            return (new OperatorCode_[size]);
        }

    }
    ;

    protected OperatorCode_(Parcel in) {
        this.network = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OperatorCode_() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(network);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
