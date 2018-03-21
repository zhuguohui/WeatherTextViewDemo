package com.zgh.weather.bean;

import java.util.List;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class WeatherIconMap {


    /**
     * icon_name : 小雨
     * weather_set : ["小雨","小雨-中雨"]
     */

    private String icon_name;
    private List<String> weather_set;

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public List<String> getWeather_set() {
        return weather_set;
    }

    public void setWeather_set(List<String> weather_set) {
        this.weather_set = weather_set;
    }
}
