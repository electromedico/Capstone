
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TransportMode implements Parcelable
{

    public String article;
    public String id;
    public String name;
    public final static Parcelable.Creator<TransportMode> CREATOR = new Creator<TransportMode>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TransportMode createFromParcel(Parcel in) {
            return new TransportMode(in);
        }

        public TransportMode[] newArray(int size) {
            return (new TransportMode[size]);
        }

    }
    ;

    protected TransportMode(Parcel in) {
        this.article = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TransportMode() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(article);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}
