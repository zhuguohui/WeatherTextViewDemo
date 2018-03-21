package com.zgh.weather.bean;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class BaiduWeatherDataBean {
    /**
     * date : 周二 03月20日 (实时：15℃)
     * dayPictureUrl : http://api.map.baidu.com/images/weather/day/duoyun.png
     * nightPictureUrl : http://api.map.baidu.com/images/weather/night/yin.png
     * weather : 多云转阴
     * wind : 无持续风向微风
     * temperature : 18 ~ 11℃
     */

    private String date;
    private String dayPictureUrl;
    private String nightPictureUrl;
    private String weather;
    private String wind;
    private String temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
