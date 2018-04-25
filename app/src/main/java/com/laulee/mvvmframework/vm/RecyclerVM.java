package com.laulee.mvvmframework.vm;

import android.util.Log;

import com.framework.core.network.HttpRequest;
import com.framework.core.network.VolleyUtil;
import com.framework.core.recycler.BaseRecyclerVM;
import com.laulee.mvvmframework.api.ApiService;
import com.laulee.mvvmframework.entity.GankEntity;
import com.laulee.mvvmframework.entity.HttpResponse;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class RecyclerVM extends BaseRecyclerVM {

    public RecyclerVM() {

    }

    public void getData() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://gank.io")
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<HttpResponse<List<GankEntity>>> responseCall = apiService.getGankData();
//        responseCall.enqueue(new Callback<HttpResponse<List<GankEntity>>>() {
//            @Override
//            public void onResponse(Call<HttpResponse<List<GankEntity>>> call, Response<HttpResponse<List<GankEntity>>> response) {
//                if (!response.body().isError()) {
//                    List<GankEntity> gankEntities = response.body().getResults();
//                    if (gankEntities != null && gankEntities.size() > 0) {
//                        for (GankEntity gankEntity : gankEntities) {
//                            items.add(new RecyclerItemVM(gankEntity));
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<HttpResponse<List<GankEntity>>> call, Throwable t) {
//
//            }
//        });
        HttpRequest<HttpResponse<List<GankEntity>>> httpRequest = new HttpRequest<HttpResponse<List<GankEntity>>>("http://gank.io/api/data/Android/10/1",
                        new HttpRequest.ICallBack<HttpResponse<List<GankEntity>>>() {
                            @Override
                            public void onSuccess(HttpResponse<List<GankEntity>> response) {
                                if (!response.isError()) {
                                    List<GankEntity> gankEntities = response.getResults();
                                    if (gankEntities != null && gankEntities.size() > 0) {
                                        for (GankEntity gankEntity : gankEntities) {
                                            items.add(new RecyclerItemVM(gankEntity));
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onError(String message) {
                                Log.d("RecyclerVM", "error=>" + message);
                            }
                        });
        VolleyUtil.addRequest(httpRequest);

//        HttpRequest<HttpResponse<List<GankEntity>>> httpRequest = new HttpRequest<HttpResponse<List<GankEntity>>>
//                ("http://gank.io/api/data/Android/10/1", null,
//                        new HttpRequest.ICallBack<HttpResponse<List<GankEntity>>>() {
//                            @Override
//                            public void onSuccess(HttpResponse<List<GankEntity>> response) {
//                                if (!response.isError()) {
//                                    List<GankEntity> gankEntities = response.getResults();
//                                    if (gankEntities != null && gankEntities.size() > 0) {
//                                        for (GankEntity gankEntity : gankEntities) {
//                                            items.add(new RecyclerItemVM(gankEntity));
//                                        }
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onError(String message) {
//                                Log.d("RecyclerVM", "error=>" + message);
//                            }
//                        });
//        VolleyUtil.addRequest(httpRequest);
    }
}