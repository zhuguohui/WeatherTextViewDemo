package com.zgh.weather.bean;

import java.util.List;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class BaiduWeatherResult {

    /**
     * error : 0
     * status : success
     * date : 2018-03-20
     * results : [{"currentCity":"绵阳","pm25":"38","index":[{"title":"穿衣","zs":"较冷","tipt":"穿衣指数","des":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},{"title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"title":"感冒","zs":"较易发","tipt":"感冒指数","des":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},{"title":"运动","zs":"较适宜","tipt":"运动指数","des":"天气较好，较适宜进行各种运动，但因湿度偏高，请适当降低运动强度。"},{"title":"紫外线强度","zs":"最弱","tipt":"紫外线强度指数","des":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}],"weather_data":[{"date":"周二 03月20日 (实时：15℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"多云转阴","wind":"无持续风向微风","temperature":"18 ~ 11℃"},{"date":"周三","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"阴转多云","wind":"无持续风向微风","temperature":"18 ~ 10℃"},{"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"22 ~ 11℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/yin.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/xiaoyu.png","weather":"阴转小雨","wind":"无持续风向微风","temperature":"20 ~ 13℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    private List<BaiduResultsBean> results;

    private static final String SUCCESS_STATUS = "success";

    public BaiduWeatherDataBean getWeatherByDayOffset(int dayOffset) {
        if (results != null && results.size() > 0 && results.get(0).getWeather_data() != null && results.get(0).getWeather_data().size() > dayOffset) {
            return results.get(0).getWeather_data().get(dayOffset);
        }
        return null;
    }

    public boolean isSuccess() {
        return SUCCESS_STATUS.equals(status);
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<BaiduResultsBean> getResults() {
        return results;
    }

    public void setResults(List<BaiduResultsBean> results) {
        this.results = results;
    }


}
