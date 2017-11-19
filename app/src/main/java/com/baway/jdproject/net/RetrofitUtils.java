package com.baway.jdproject.net;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 郑文杰 on 2017/11/2.
 */

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtil;

    private RetrofitUtils() {

    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtils();
                }
            }
        }
        return retrofitUtil;
    }


    private static Retrofit retrofit;

    public static synchronized Retrofit getRetrofit(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("tag", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //实例化OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(3000, TimeUnit.SECONDS)
                .build();
        //进行网络请求
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public <T>T getApiService(String url,Class<T> cl){
        Retrofit retrofit = getRetrofit(url);
        return retrofit.create(cl);
    }

}
