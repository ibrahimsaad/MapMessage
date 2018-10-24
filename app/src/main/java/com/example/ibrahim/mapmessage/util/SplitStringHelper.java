package com.example.ibrahim.mapmessage.util;

public class SplitStringHelper {

    public static String getEmotion(String message) {
        String emotion = message
                .substring(message.indexOf("sentiment: ") + 11);
        return emotion;
    }


    public static String getMessage(String message) {
        String mMessage = message
                .substring(message.indexOf("message: ") + 9
                        , message.indexOf(", s"));

        return mMessage;
    }


    public static String getCityName(String message) {
        String cityName = message
                .substring(message.indexOf("message: ") + 9
                        , message.indexOf(", s"));
        if (cityName.contains("from")) {
            return cityName.substring(cityName.indexOf("from ") + 5);
        } else {
            return cityName;
        }
    }
}
