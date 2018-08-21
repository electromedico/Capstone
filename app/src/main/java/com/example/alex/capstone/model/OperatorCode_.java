
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatorCode_ implements Parcelable
{

    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("value")
    @Expose
    private String value;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public OperatorCode_() {
    }

    /**
     * Constructor
     * @param value value
     * @param network network
     */
    public OperatorCode_(String network, String value) {
        super();
        this.network = network;
        this.value = value;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(network);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
