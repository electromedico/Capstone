
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalText implements Parcelable
{

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private String text;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lang);
        dest.writeValue(text);
    }

    public int describeContents() {
        return  0;
    }

}
