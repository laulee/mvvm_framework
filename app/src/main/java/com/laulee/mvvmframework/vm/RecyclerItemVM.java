package com.laulee.mvvmframework.vm;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.framework.core.base.BaseVM;
import com.framework.core.manager.UiManager;
import com.framework.core.recycler.ItemVar;
import com.framework.core.ui.WebActivity;
import com.laulee.mvvmframework.BR;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.entity.GankEntity;
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class RecyclerItemVM extends BaseVM implements ItemVar {

    public ObservableField<String> title = new ObservableField<>();

    public ObservableField<String> image = new ObservableField<>();

    private Context context;
    private ZhihuThemeEntity.StoriesBean gankEntity;

    public RecyclerItemVM(Context context, ZhihuThemeEntity.StoriesBean entity) {
        this.context = context;
        this.gankEntity = entity;
        this.title.set(entity.getTitle());
        if (entity.getImages() != null && entity.getImages().size() > 0) {
            this.image.set(entity.getImages().get(0));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_recycler_view;
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    public void onClick(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title.get());
        map.put("loadUrl", gankEntity.getTitle());
        UiManager.switcher(context, map, WebActivity.class);
    }
}