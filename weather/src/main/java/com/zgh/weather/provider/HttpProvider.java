package com.zgh.weather.provider;

import rx.Observable;

/**
 * Created by zhuguohui on 2018/3/20.
 */

public interface HttpProvider {
    Observable<String> getString(String url);
}
