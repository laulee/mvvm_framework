package com.laulee.mvvmframework.vm;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.support.v4.app.FragmentActivity;

import com.framework.core.base.BaseVM;
import com.framework.core.recycler.ItemVar;
import com.laulee.mvvmframework.BR;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.entity.ZhihuThemeEntity;

import java.util.List;

/**
 * Created by laulee on 2018/5/2.
 */

public class IndexBannerVM extends BaseVM implements ItemVar {

    public ObservableFloat displayBannerHeight = new ObservableFloat();
    public ObservableArrayList<ZhihuThemeEntity.StoriesBean> bannerListBean = new ObservableArrayList<>();

    public IndexBannerVM(Activity activity, List<ZhihuThemeEntity.StoriesBean> storiesBeans) {
        displayBannerHeight.set(420);
        bannerListBean.addAll(storiesBeans);
    }

    @Override
    public int getLayoutId() {
        return R.layout.index_recycler_banner;
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }
}
