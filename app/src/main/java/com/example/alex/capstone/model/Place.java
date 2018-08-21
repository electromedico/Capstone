
package com.example.alex.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable
{

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("network")
    @Expose
    private String network;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("x")
    @Expose
    private String x;
    @SerializedName("y")
    @Expose
    private String y;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("typeCompressed")
    @Expose
    private String typeCompressed;
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

    /**
     * No args constructor for use in serialization
     * 
     */
    public Place() {
    }

    /**
     * Constructor
     * @param cityName cityName
     * @param label label
     * @param type type
     * @param postcode postcode
     * @param network network
     * @param typeCompressed typeCompressed
     * @param id id
     * @param category category
     * @param rank rank
     * @param address address
     * @param className className
     * @param key key
     * @param y y
     * @param x x
     */
    public Place(String category, String className, String id, String key, String label, String network, String rank, String x, String y, String address, String cityName, String postcode, String type, String typeCompressed) {
        super();
        this.category = category;
        this.className = className;
        this.id = id;
        this.key = key;
        this.label = label;
        this.network = network;
        this.rank = rank;
        this.x = x;
        this.y = y;
        this.address = address;
        this.cityName = cityName;
        this.postcode = postcode;
        this.type = type;
        this.typeCompressed = typeCompressed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCompressed() {
        return typeCompressed;
    }

    public void setTypeCompressed(String typeCompressed) {
        this.typeCompressed = typeCompressed;
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
