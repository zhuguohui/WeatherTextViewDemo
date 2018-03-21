package com.zgh.lib;

import java.util.List;

/**
 * Created by zhuguohui on 2018/3/21.
 */

public class BaiduResult {

    /**
     * error : 0
     * status : success
     * date : 2018-03-21
     * results : [{"currentCity":"仪陇","pm25":"","index":[{"date":"周三 03月21日 (实时：12℃)","title":"穿衣","zs":"较舒适","tipt":"穿衣指数","des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},{"date":"周三 03月21日 (实时：12℃)","title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"date":"周三 03月21日 (实时：12℃)","title":"感冒","zs":"较易发","tipt":"感冒指数","des":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},{"title":"运动","zs":"适宜","tipt":"运动指数","des":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"title":"紫外线强度","zs":"弱","tipt":"紫外线强度指数","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}],"weather_data":[{"date":"周三 03月21日 (实时：12℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"19 ~ 9℃"},{"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"20 ~ 11℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阵雨转阴","wind":"无持续风向微风","temperature":"19 ~ 13℃"},{"date":"周六","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阵雨转阴","wind":"无持续风向微风","temperature":"18 ~ 12℃"}]}]
     */

    private int error;
    private String status;
    private String date;
    private List<ResultsBean> results;

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

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * currentCity : 仪陇
         * pm25 :
         * index : [{"date":"周三 03月21日 (实时：12℃)","title":"穿衣","zs":"较舒适","tipt":"穿衣指数","des":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},{"date":"周三 03月21日 (实时：12℃)","title":"洗车","zs":"较适宜","tipt":"洗车指数","des":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"date":"周三 03月21日 (实时：12℃)","title":"感冒","zs":"较易发","tipt":"感冒指数","des":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},{"title":"运动","zs":"适宜","tipt":"运动指数","des":"天气较好，赶快投身大自然参与户外运动，尽情感受运动的快乐吧。"},{"title":"紫外线强度","zs":"弱","tipt":"紫外线强度指数","des":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}]
         * weather_data : [{"date":"周三 03月21日 (实时：12℃)","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"19 ~ 9℃"},{"date":"周四","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/duoyun.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/duoyun.png","weather":"多云","wind":"无持续风向微风","temperature":"20 ~ 11℃"},{"date":"周五","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阵雨转阴","wind":"无持续风向微风","temperature":"19 ~ 13℃"},{"date":"周六","dayPictureUrl":"http://api.map.baidu.com/images/weather/day/zhenyu.png","nightPictureUrl":"http://api.map.baidu.com/images/weather/night/yin.png","weather":"阵雨转阴","wind":"无持续风向微风","temperature":"18 ~ 12℃"}]
         */

        private String currentCity;
        private String pm25;
        private List<IndexBean> index;
        private List<WeatherDataBean> weather_data;

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

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<WeatherDataBean> getWeather_data() {
            return weather_data;
        }

        public void setWeather_data(List<WeatherDataBean> weather_data) {
            this.weather_data = weather_data;
        }

        public static class IndexBean {
            /**
             * date : 周三 03月21日 (实时：12℃)
             * title : 穿衣
             * zs : 较舒适
             * tipt : 穿衣指数
             * des : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             */

            private String date;
            private String title;
            private String zs;
            private String tipt;
            private String des;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getZs() {
                return zs;
            }

            public void setZs(String zs) {
                this.zs = zs;
            }

            public String getTipt() {
                return tipt;
            }

            public void setTipt(String tipt) {
                this.tipt = tipt;
            }

            public String getDes() {
                return des;
            }

            public void setDes(String des) {
                this.des = des;
            }
        }

        public static class WeatherDataBean {
            /**
             * date : 周三 03月21日 (实时：12℃)
             * dayPictureUrl : http://api.map.baidu.com/images/weather/day/duoyun.png
             * nightPictureUrl : http://api.map.baidu.com/images/weather/night/duoyun.png
             * weather : 多云
             * wind : 无持续风向微风
             * temperature : 19 ~ 9℃
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
    }
}
