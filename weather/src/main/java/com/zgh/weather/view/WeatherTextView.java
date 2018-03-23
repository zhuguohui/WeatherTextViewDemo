package com.zgh.weather.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;


import com.zgh.weather.R;
import com.zgh.weather.WeatherMananger;
import com.zgh.weather.bean.WeatherResult;


import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class WeatherTextView extends android.support.v7.widget.AppCompatTextView {
    private long lastLoadTime = 0;//上一次加载的时间
    private static final int DEFAULT_ICON_COLOR = Color.parseColor("#6C6C6C");
    private static final int DEFAULT_ICON_SIZE = 42;
    private static final String DEFAULT_FORMAT_STRING = "{weather_data[0].weather}";
    private static final int WHAT_UPDATE = 1;
    private static final int STOP_UPDATE = -1;


    private WeatherResult mWeatherResult = null;
    private Drawable weatherDrawable;
    private static final String DEFAULT_CITY_NAME = "成都";
    private int iconColor;
    private int iconSize = 42;
    private String cityName = "成都";
    private int dayOffset = 0;
    private String formatString;
    private Drawable[] iconArray = new Drawable[4];
    private int iconLocationIndex = 0;
    private long autoUpdateTime = STOP_UPDATE;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_UPDATE:
                    if (autoUpdateTime != STOP_UPDATE) {
                        loadWeatherData(true);
                        sendEmptyMessageDelayed(WHAT_UPDATE,autoUpdateTime);
                    } else {
                        removeMessages(WHAT_UPDATE);
                    }
                    break;
            }
        }
    };

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
        autoUpdateTime = typedArray.getInt(R.styleable.WeatherTextView_WeatherTextViewAutoUpdateTime, STOP_UPDATE);
        if (TextUtils.isEmpty(cityName)) {
            cityName = DEFAULT_CITY_NAME;
        }
        if (TextUtils.isEmpty(formatString)) {
            formatString = DEFAULT_FORMAT_STRING;
        }
        if (!isInEditMode()) {
            loadWeatherData(true);
        }
        typedArray.recycle();
        setOnClickListener(v -> {
            loadWeatherData(false);
        });
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.sendEmptyMessage(WHAT_UPDATE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //停止消息发送，防止内存溢出
        handler.removeMessages(WHAT_UPDATE);
    }

    public void setAutoUpdateTime(long autoUpdateTime) {
        this.autoUpdateTime = autoUpdateTime;
        handler.sendEmptyMessage(WHAT_UPDATE);
    }

    private void loadWeatherData(boolean autoLoad) {
        if (autoLoad) {
            //判断是否加载过了
            long passTime = System.currentTimeMillis() - lastLoadTime;
            if (mWeatherResult != null && weatherDrawable != null && passTime < autoUpdateTime) {
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
        //设置图标大小
        weatherDrawable.setBounds(0, 0, iconSize, iconSize);
        iconArray[0] = null;
        iconArray[1] = null;
        iconArray[2] = null;
        iconArray[3] = null;
        //通过iconLocationIndex 控制图标出现的位置
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
