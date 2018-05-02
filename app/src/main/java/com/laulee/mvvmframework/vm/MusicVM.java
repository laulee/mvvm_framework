package com.laulee.mvvmframework.vm;

import android.content.Context;

import com.framework.core.network.retrofit.RetrofitHelper;
import com.framework.core.recycler.BaseRecyclerVM;
import com.laulee.mvvmframework.api.ApiService;
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by laulee on 2018/5/2.
 */

public class MusicVM extends BaseRecyclerVM {

    private Context context;

    public MusicVM(Context context) {
        this.context = context;
    }

    public void getMusic() {
        ApiService apiService = RetrofitHelper.getService(ApiService.class);
        apiService.getMusic().enqueue(new Callback<ZhihuThemeEntity>() {
            @Override
            public void onResponse(Call<ZhihuThemeEntity> call, Response<ZhihuThemeEntity> response) {
                ZhihuThemeEntity zhihuThemeEntity = response.body();
                List<ZhihuThemeEntity.StoriesBean> storiesBeans = zhihuThemeEntity.getStories();
                if (storiesBeans != null) {
                    for (ZhihuThemeEntity.StoriesBean storiesBean : storiesBeans) {
                        items.add(new RecyclerItemVM(context, storiesBean));
                    }
                }
            }

            @Override
            public void onFailure(Call<ZhihuThemeEntity> call, Throwable t) {

            }
        });
    }
}
