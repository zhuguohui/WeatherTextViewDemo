package com.zgh.weathertextviewdemo;

import android.app.Application;

import com.zgh.weather.WeatherMananger;

/**
 * Created by zhuguohui on 2018/3/21.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WeatherMananger.init(this, new MyHttpProvider(),"qpwrPxG2fx0TG3hLxerp8uMK");
    }
}
