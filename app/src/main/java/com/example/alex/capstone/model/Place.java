
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Place implements Parcelable
{

    public String category;
    public String className;
    public String id;
    public String key;
    public String label;
    public String network;
    public String rank;
    public String x;
    public String y;
    public String address;
    public String cityName;
    public String postcode;
    public String type;
    public String typeCompressed;
    public final static Parcelable.Creator<Place> CREATOR = new Creator<Place>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return (new Place[size]);
        }

    }
    ;

    protected Place(Parcel in) {
        this.category = ((String) in.readValue((String.class.getClassLoader())));
        this.className = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.key = ((String) in.readValue((String.class.getClassLoader())));
        this.label = ((String) in.readValue((String.class.getClassLoader())));
        this.network = ((String) in.readValue((String.class.getClassLoader())));
        this.rank = ((String) in.readValue((String.class.getClassLoader())));
        this.x = ((String) in.readValue((String.class.getClassLoader())));
        this.y = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.cityName = ((String) in.readValue((String.class.getClassLoader())));
        this.postcode = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.typeCompressed = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Place() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(category);
        dest.writeValue(className);
        dest.writeValue(id);
        dest.writeValue(key);
        dest.writeValue(label);
        dest.writeValue(network);
        dest.writeValue(rank);
        dest.writeValue(x);
        dest.writeValue(y);
        dest.writeValue(address);
        dest.writeValue(cityName);
        dest.writeValue(postcode);
        dest.writeValue(type);
        dest.writeValue(typeCompressed);
    }

    public int describeContents() {
        return  0;
    }

}
