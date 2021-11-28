package com.irrigation.kafkaproducer.data.entity;

import java.util.StringJoiner;

public class WeatherApi {

    public static String getUrl(City city, long time, String apikey) {

        StringJoiner urlJoiner = new StringJoiner("");

        urlJoiner.add("http://api.openweathermap.org/data/2.5/onecall/timemachine");
        urlJoiner.add("?lat=" + city.getLat().toString());
        urlJoiner.add("&lon=" + city.getLon().toString());
        urlJoiner.add("&dt=" + Long.toString(time));
        urlJoiner.add("&appid=" + apikey);

        return urlJoiner.toString();
    }

}
