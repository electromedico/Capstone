
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stop implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("operatorCode")
    @Expose
    private String operatorCode;
    @SerializedName("handicappedCompliance")
    @Expose
    private String handicappedCompliance;

    public final static Parcelable.Creator<Stop> CREATOR = new Creator<Stop>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        public Stop[] newArray(int size) {
            return (new Stop[size]);
        }

    }
    ;

    protected Stop(Parcel in) {
        this.handicappedCompliance = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.operatorCode = ((String) in.readValue((String.class.getClassLoader())));
    }


    /**
     * No args constructor for use in serialization
     * 
     */
    public Stop() {
    }

    /**
     * 
     * @param id
     * @param operatorCode
     * @param name
     */
    public Stop(String id, String name, String operatorCode) {
        super();
        this.id = id;
        this.name = name;
        this.operatorCode = operatorCode;
    }

    /**
     *
     * @param id
     * @param handicappedCompliance
     * @param operatorCode
     * @param name
     */
    public Stop(String handicappedCompliance, String id, String name, String operatorCode) {
        super();
        this.handicappedCompliance = handicappedCompliance;
        this.id = id;
        this.name = name;
        this.operatorCode = operatorCode;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(operatorCode);
        dest.writeValue(handicappedCompliance);
    }

    public int describeContents() {
        return  0;
    }

}
