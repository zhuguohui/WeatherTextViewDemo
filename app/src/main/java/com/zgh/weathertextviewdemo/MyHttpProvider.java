package com.zgh.weathertextviewdemo;

import com.zgh.weather.provider.HttpProvider;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zhuguohui on 2018/3/21.
 */

public class MyHttpProvider implements HttpProvider {
    @Override
    public Observable<String> getString(final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象
                Request request = new Request.Builder()
                        .url(url)//请求接口。如果需要传参拼接到接口后面。
                        .build();//创建Request 对象
                Response response = null;
                try {
                    response = client.newCall(request).execute();//得到Response 对象
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

            }
        }).subscribeOn(Schedulers.io());

    }
}
