
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Text_ implements Parcelable
{

    public String lang;
    public String text;
    public final static Parcelable.Creator<Text_> CREATOR = new Creator<Text_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Text_ createFromParcel(Parcel in) {
            return new Text_(in);
        }

        public Text_[] newArray(int size) {
            return (new Text_[size]);
        }

    }
    ;

    protected Text_(Parcel in) {
        this.lang = ((String) in.readValue((String.class.getClassLoader())));
        this.text = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Text_() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lang);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}
