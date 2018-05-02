package com.laulee.mvvmframework.api;

import com.laulee.mvvmframework.entity.GankResponse;
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public interface ApiService {

    @GET("/api/4/theme/11")
    public Call<ZhihuThemeEntity> getGankData();

    //电影日报
    @GET("/api/4/theme/3")
    public Call<ZhihuThemeEntity> getMovies();

    //音乐日报
    @GET("/api/4/theme/7")
    public Call<ZhihuThemeEntity> getMusic();
}
