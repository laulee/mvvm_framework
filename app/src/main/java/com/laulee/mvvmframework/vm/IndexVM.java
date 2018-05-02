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
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;

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
        Call<ZhihuThemeEntity> gankResponseCall = apiService.getGankData();
        gankResponseCall.enqueue(new RequestCallBack<ZhihuThemeEntity>(fragment.getActivity()) {

            @Override
            public void onSuccess(ZhihuThemeEntity response) {
                items.clear();
                List<ZhihuThemeEntity.StoriesBean> gankEntities = response.getStories();
                if (gankEntities != null && gankEntities.size() > 0) {
                    items.add(new IndexBannerVM(fragment.getActivity(), response.getStories()));
                    for (ZhihuThemeEntity.StoriesBean storiesBean : gankEntities) {
                        items.add(new RecyclerItemVM(fragment.getActivity(), storiesBean));
                    }
                }
            }

            @Override
            public void onError(String message) {
                Log.d("error", message);
            }
        });
    }
}
