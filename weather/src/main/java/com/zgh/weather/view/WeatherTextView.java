package com.zgh.weather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;


import com.zgh.weather.R;
import com.zgh.weather.WeatherMananger;
import com.zgh.weather.bean.BaiduWeatherDataBean;
import com.zgh.weather.bean.WeatherResult;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class WeatherTextView extends android.support.v7.widget.AppCompatTextView {
    private static long lastLoadTime = 0;//上一次加载的时间
    private static final long AUTO_LOAD_TIME_SPAN = 10 * 60 * 1000;//自动加载事件间隔10分钟
    private static WeatherResult mWeatherResult = null;
    private static Drawable weatherDrawable;
    private static final int DEFAULT_ICON_COLOR = Color.parseColor("#6C6C6C");
    private static final int DEFAULT_ICON_SIZE = 42;
    private static final String DEFAULT_FORMAT_STRING = "{weather_data[0].weather}";

    private static final String DEFAULT_CITY_NAME = "成都";
    private int iconColor;
    private int iconSize = 42;
    private String cityName = "成都";
    private int dayOffset = 0;
    private String formatString;
    private Drawable[] iconArray = new Drawable[4];
    private int iconLocationIndex = 0;

    public WeatherTextView(Context context) {
        this(context, null);
    }

    public WeatherTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WeatherTextView);
        iconColor = typedArray.getColor(R.styleable.WeatherTextView_WeatherTextViewIconColor, DEFAULT_ICON_COLOR);
        iconSize = typedArray.getDimensionPixelSize(R.styleable.WeatherTextView_WeatherTextViewIconSize, DEFAULT_ICON_SIZE);
        cityName = typedArray.getString(R.styleable.WeatherTextView_WeatherTextViewCityName);
        formatString = typedArray.getString(R.styleable.WeatherTextView_WeatherTextViewFormatString);
        iconLocationIndex = typedArray.getInt(R.styleable.WeatherTextView_WeatherTextViewIconLocation, 0);
        dayOffset = typedArray.getInt(R.styleable.WeatherTextView_WeatherTextViewDayOffset, 0);
        if (TextUtils.isEmpty(cityName)) {
            cityName = DEFAULT_CITY_NAME;
        }
        if (TextUtils.isEmpty(formatString)) {
            formatString = DEFAULT_FORMAT_STRING;
        }
        typedArray.recycle();
        if (!isInEditMode()) {
            post(() -> {
                loadWeatherData(true);
            });
        }
        setOnClickListener(v -> {
            loadWeatherData(false);
        });
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void loadWeatherData(boolean autoLoad) {
        if (autoLoad) {
            //判断是否加载过了
            long passTime = System.currentTimeMillis() - lastLoadTime;
            if (mWeatherResult != null && weatherDrawable != null && passTime < AUTO_LOAD_TIME_SPAN) {
                setUIByWeather();
                return;
            }
        }
        setText("加载中...");
        WeatherMananger.getWeatherInfo(getContext(), cityName, dayOffset, iconColor, formatString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    mWeatherResult = result;
                    weatherDrawable = result.getWeatherDrawable();
                    lastLoadTime = System.currentTimeMillis();
                    setUIByWeather();
                }, throwable -> {
                    throwable.printStackTrace();
                    mWeatherResult = null;
                    setText("点击重试");
                });
    }

    private void setUIByWeather() {
        setText(mWeatherResult.getWeatherString());
        weatherDrawable.setBounds(0, 0, iconSize, iconSize);
        iconArray[0] = null;
        iconArray[1] = null;
        iconArray[2] = null;
        iconArray[3] = null;
        iconArray[iconLocationIndex] = weatherDrawable;
        setCompoundDrawables(iconArray[0], iconArray[1], iconArray[2], iconArray[3]);
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
        loadWeatherData(false);
    }

    public void setIconColor(int iconColor) {
        this.iconColor = iconColor;
        loadWeatherData(false);
    }

    public void setFormatString(String formatString) {
        this.formatString = formatString;
        loadWeatherData(false);
    }
}
