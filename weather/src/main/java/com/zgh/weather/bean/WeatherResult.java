package com.zgh.weather.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class WeatherResult {

    String weatherString;
    Drawable weatherDrawable;
    long loadTime = 0;
    String formatString;
    int dayOffset = 0;

    public WeatherResult(String weatherString, Drawable weatherDrawable, long loadTime, String formatString, int dayOffset) {
        this.weatherString = weatherString;
        this.weatherDrawable = weatherDrawable;
        this.loadTime = loadTime;
        this.formatString = formatString;
        this.dayOffset = dayOffset;
    }

    public int getDayOffset() {
        return dayOffset;
    }

    public String getFormatString() {
        return formatString;
    }

    public String getWeatherString() {
        return weatherString;
    }

    public void setWeatherString(String weatherString) {
        this.weatherString = weatherString;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public Drawable getWeatherDrawable() {
        return weatherDrawable;
    }

    public void setWeatherDrawable(Drawable weatherDrawable) {
        this.weatherDrawable = weatherDrawable;
    }
}
