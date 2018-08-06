
package com.example.alex.capstone.model.getJourneysQueryModel;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Text_ implements Parcelable
{

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private String text;
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
