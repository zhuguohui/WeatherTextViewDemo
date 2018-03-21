package com.zgh.lib;

import com.google.gson.Gson;

public class myClass {


    public static void main(String[] args) {
        Gson gson = new Gson();
        BaiduResult baiduResult = gson.fromJson(DataSupport.TEST_DATA, BaiduResult.class);
        String testFormat = "当前城市{currentCity},今天天气:{weather_data[3].weather},星期:{weather_data[0].date}";
        String showStr = JSONObjectValueGetter.getValueByFormatString(baiduResult.getResults().get(0), testFormat);
        System.out.println(showStr);
    }
}
