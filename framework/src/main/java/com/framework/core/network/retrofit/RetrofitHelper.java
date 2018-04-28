package com.framework.core.network.retrofit;

import android.util.Log;

import com.framework.core.BuildConfig;
import com.framework.core.app.BaseApplication;
import com.framework.core.network.retrofit.intercepter.BasicParametersInterceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by laulee on 2018/4/27.
 */

public class RetrofitHelper {

    private static RetrofitHelper retrofitHelper;
    private static Map<String, Object> retrofitMap = new HashMap<>();
    private static Retrofit retrofit;

    /**
     * 初始化
     *
     * @param baseApplication
     */
    public static void initConfig(BaseApplication baseApplication) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (baseApplication.isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("Retrofit", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        okHttpClientBuilder.addInterceptor(new BasicParametersInterceptor(baseApplication));
        retrofit = new Retrofit.Builder().baseUrl(baseApplication.getServer())
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * 构造函数
     *
     * @param baseUrl
     */
    private RetrofitHelper(String baseUrl) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.d("Retrofit", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            okHttpClientBuilder.addInterceptor(loggingInterceptor);
        }
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 获取指定service
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> serviceClass) {
        if (retrofitMap.get(serviceClass) != null) {
            return (T) retrofitMap.get(serviceClass);
        } else {
            T service = retrofit.create(serviceClass);
            retrofitMap.put(serviceClass.getName(), service);
            return service;
        }
    }
}
