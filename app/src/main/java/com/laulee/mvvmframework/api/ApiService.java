package com.laulee.mvvmframework.api;

import com.laulee.mvvmframework.entity.GankResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public interface ApiService {

    @GET("/api/data/Android/10/1")
    public Call<GankResponse> getGankData(@Query("name") String object);
}
