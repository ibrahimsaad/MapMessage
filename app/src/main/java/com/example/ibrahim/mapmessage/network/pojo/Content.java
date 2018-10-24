package com.example.ibrahim.mapmessage.network.pojo;

//ss

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;


public class Content {

    @SerializedName("$t")
    private String message;

    @SerializedName("type")
    private String type;

    private String emotion ;

    private LatLng coordinate ;

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public LatLng getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(LatLng coordinate) {
        this.coordinate = coordinate;
    }

    public void setMessage(String T) {
        this.message = T;
    }

    public String getMessage() {
        return message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return
                "Content{" +
                        "$t = '" + message + '\'' +
                        ",type = '" + type + '\'' +
                        "}";
    }
}