
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorCode implements Parcelable
{

    @SerializedName("operatorCode")
    @Expose
    private OperatorCode_ operatorCode;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public OperatorCode() {
    }

    /**
     * 
     * @param operatorCode
     */
    public OperatorCode(OperatorCode_ operatorCode) {
        super();
        this.operatorCode = operatorCode;
    }

    public OperatorCode_ getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(OperatorCode_ operatorCode) {
        this.operatorCode = operatorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(operatorCode);
    }

    public int describeContents() {
        return  0;
    }

}
