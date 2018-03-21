package com.zgh.weather.bean;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public class BaiduIndexBean {
    /**
     * title : 穿衣
     * zs : 较冷
     * tipt : 穿衣指数
     * des : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
     */

    private String title;
    private String zs;
    private String tipt;
    private String des;

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
