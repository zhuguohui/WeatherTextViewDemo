package com.zgh.weather.bean;

import java.util.List;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class BaiduResultsBean {
    /**
     * currentCity : 绵阳
     * pm25 : 38
     * index : [{"title":"穿衣","zs":"较冷","tipt":"穿衣指数","des":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},{"title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"title":"感冒","zs":"较易发","tipt":"感冒指数","des":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},{"title":"运动","zs":"较适宜","tipt":"运动指数","des":"天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。"},{"title":"紫外线强度","zs":"最弱","tipt":"紫外线强度指数","des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}]
     * weather_data : [{"date":"周二 03月20日 (实时：15℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"多云转阴","wind":"无持续风向微风","temperature":"18 ~ 11℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阴转多云","wind":"无持续风向微风","temperature":"18 ~ 10℃"},{"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"22 ~ 11℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"阴转小雨","wind":"无持续风向微风","temperature":"20 ~ 13℃"}]
     */

    private String currentCity;
    private String pm25;
    private List<BaiduIndexBean> index;
    private List<BaiduWeatherDataBean> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<BaiduIndexBean> getIndex() {
        return index;
    }

    public void setIndex(List<BaiduIndexBean> index) {
        this.index = index;
    }

    public List<BaiduWeatherDataBean> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<BaiduWeatherDataBean> weather_data) {
        this.weather_data = weather_data;
    }


}
