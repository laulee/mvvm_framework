package com.laulee.mvvmframework.api;

import android.database.Observable;

import com.laulee.mvvmframework.entity.GankEntity;
import com.laulee.mvvmframework.entity.HttpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public interface ApiService {

    @GET("/api/data/Android/10/1")
    public Call<HttpResponse<List<GankEntity>>> getGankData();
}
