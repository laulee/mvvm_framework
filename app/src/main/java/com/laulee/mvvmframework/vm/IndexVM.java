package com.laulee.mvvmframework.vm;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.framework.core.recycler.BaseRecyclerVM;
import com.laulee.mvvmframework.api.ApiService;
import com.laulee.mvvmframework.entity.GankEntity;
import com.laulee.mvvmframework.entity.HttpResponse;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class IndexVM extends BaseRecyclerVM {

    private Fragment fragment;

    public IndexVM(Fragment indexFragment) {
        this.fragment = indexFragment;
    }

    public void getData() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<HttpResponse<List<GankEntity>>> responseCall = apiService.getGankData();
        responseCall.enqueue(new Callback<HttpResponse<List<GankEntity>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<GankEntity>>> call, Response<HttpResponse<List<GankEntity>>> response) {
                if (!response.body().isError()) {
                    List<GankEntity> gankEntities = response.body().getResults();
                    if (gankEntities != null && gankEntities.size() > 0) {
                        for (GankEntity gankEntity : gankEntities) {
                            items.add(new RecyclerItemVM(gankEntity));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpResponse<List<GankEntity>>> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
