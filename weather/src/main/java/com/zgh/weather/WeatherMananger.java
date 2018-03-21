package com.zgh.weather;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zgh.weather.bean.BaiduWeatherDataBean;
import com.zgh.weather.bean.BaiduWeatherResult;
import com.zgh.weather.bean.WeatherIconMap;
import com.zgh.weather.bean.WeatherResult;
import com.zgh.weather.config.NetAddress;
import com.zgh.weather.provider.HttpProvider;
import com.zgh.weather.util.JSONObjectValueGetter;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class WeatherMananger {
    private static boolean initSuccess = false;
    private static HttpProvider httpProvider;
    private static List<WeatherIconMap> weatherIconMaps;
    private static final String DEAFULT_ICON_NAME = "阴";
    private static Gson gson = new Gson();

    public static void init(Context context, HttpProvider httpProvider) {
        if (httpProvider == null) {
            throw new IllegalArgumentException("HttpProvider must be not null");
        }
        if (context == null) {
            throw new IllegalArgumentException("context must be not null");
        }
        WeatherMananger.httpProvider = httpProvider;

        if (!readConfigFromRaw(context)) {
            throw new IllegalArgumentException("read weather config file fail");
        }


    }

    private static boolean readConfigFromRaw(Context context) {
        InputStream in = context.getResources().openRawResource(R.raw.weather_config);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        String jsonStr = "";
        try {
            while ((len = in.read(bytes)) != -1) {
                baos.write(bytes, 0, len);
            }
            jsonStr = baos.toString();
            baos.close();
            in.close();
            weatherIconMaps = gson.fromJson(jsonStr, new TypeToken<List<WeatherIconMap>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 获取城市的天气
     *
     * @param cityName     城市名称，例如：绵阳
     * @param iconColor    需要将icon渲染成的颜色 ，例如红色为"#ff0000" ,如果为空则返回白色图标
     * @param dayOffset    获取第几天的天气，0表示今天 1表示明天，2表示后天。最多为3
     * @param formatString 可以使用占位符类获取相关数据。比如果获取城市+今天的天气可以使用:"城市{currentCity},今天天气{weather_data[0].weather}"
     *                     <p>规则如下：
     *                     <p> 1.占位符最外层为{} </p>
     *                     <p> 2.如果要获取数组下面的某个元素则可通过 arryName[index] 方式获取
     *                     <p> 3.如果要获取多级则可通过 valueA.valueB 的方式获取
     *                     <p> 4.如果没有相关元素则显示[null]
     *                     <p> 5.如果元素出错，比如数组越界则显示[error]
     * @return
     */
    public static Observable<WeatherResult> getWeatherInfo(final Context context, String cityName, int dayOffset, final int iconColor, String formatString) {
        String url = String.format(NetAddress.BAIDU_WEATHER_API, URLEncoder.encode(cityName));
        return httpProvider.getString(url)
                .subscribeOn(Schedulers.io())
                .map(str -> {
                    BaiduWeatherResult baiduWeatherResult = gson.fromJson(str, BaiduWeatherResult.class);
                    BaiduWeatherDataBean todayWeather = baiduWeatherResult.getWeatherByDayOffset(dayOffset);
                    if (todayWeather == null) {
                        throw new RuntimeException("获取天气失败");
                    }
                    String weatherShowStr = JSONObjectValueGetter.getValueByFormatString(baiduWeatherResult.getResults().get(0), formatString);
                    String weather = todayWeather.getWeather();
                    String weatherStr = weather;
                    if (weather.contains("转")) {
                        weatherStr = weather.substring(weather.indexOf("转") + 1);
                    }
                    String iconName = null;
                    if (weatherIconMaps != null) {
                        for (WeatherIconMap iconMap : weatherIconMaps) {
                            if (iconMap.getWeather_set().contains(weatherStr)) {
                                iconName = iconMap.getIcon_name();
                                break;
                            }
                        }
                    }
                    if (TextUtils.isEmpty(iconName)) {
                        iconName = DEAFULT_ICON_NAME;
                    }
                    //读取assets下的文件
                    String fileName = iconName + ".png";
                    Drawable drawable = null;
                    try {
                        InputStream in = context.getClass().getClassLoader().getResourceAsStream("assets/weather/" + fileName);
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        drawable = new BitmapDrawable(bitmap);
                        in.close();
                        drawable = tintDrawable(drawable, ColorStateList.valueOf(iconColor));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WeatherResult weatherResult = new WeatherResult(weatherShowStr, drawable, System.currentTimeMillis(), formatString, dayOffset);
                    weatherResult.setWeatherDrawable(drawable);
                    return weatherResult;
                });
    }


    private static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }


}
