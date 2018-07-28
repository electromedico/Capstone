
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ArrivalText implements Parcelable
{

    public String lang;
    public String text;
    public final static Parcelable.Creator<ArrivalText> CREATOR = new Creator<ArrivalText>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ArrivalText createFromParcel(Parcel in) {
            return new ArrivalText(in);
        }

        public ArrivalText[] newArray(int size) {
            return (new ArrivalText[size]);
        }

    }
    ;

    protected ArrivalText(Parcel in) {
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ArrivalText() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lang);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}
