package com.laulee.mvvmframework.vm;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.framework.core.network.retrofit.RetrofitHelper;
import com.framework.core.network.retrofit.callback.RequestCallBack;
import com.framework.core.recycler.BaseRecyclerVM;
import com.laulee.mvvmframework.api.ApiService;
import com.laulee.mvvmframework.entity.GankEntity;
import com.laulee.mvvmframework.entity.GankResponse;

import java.util.List;

import retrofit2.Call;

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
        ApiService apiService = RetrofitHelper.getService(ApiService.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","name");

        Call<GankResponse> gankResponseCall = apiService.getGankData("name");
        gankResponseCall.enqueue(new RequestCallBack<GankResponse>(fragment.getActivity()) {

            @Override
            public void onSuccess(GankResponse response) {
                List<GankEntity> gankEntities = response.getResults();
                if (gankEntities != null && gankEntities.size() > 0) {
                    for (GankEntity gankEntity : gankEntities) {
                        items.add(new RecyclerItemVM(gankEntity));
                    }
                }
            }

            @Override
            public void onError(String message) {
                Log.d("error",message);
            }
        });
    }
}
