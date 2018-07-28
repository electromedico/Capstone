
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OperatorCode implements Parcelable
{

    public OperatorCode_ operatorCode;
    public final static Parcelable.Creator<OperatorCode> CREATOR = new Creator<OperatorCode>() {


        @SuppressWarnings({
            "unchecked"
        })
        public OperatorCode createFromParcel(Parcel in) {
            return new OperatorCode(in);
        }

        public OperatorCode[] newArray(int size) {
            return (new OperatorCode[size]);
        }

    }
    ;

    protected OperatorCode(Parcel in) {
        this.operatorCode = ((OperatorCode_) in.readValue((OperatorCode_.class.getClassLoader())));
    }

    public OperatorCode() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(operatorCode);
    }

    public int describeContents() {
        return  0;
    }

}
