package com.laulee.mvvmframework.vm;

import android.databinding.ObservableField;
import com.framework.core.base.BaseVM;
import com.framework.core.recycler.ItemVar;
import com.laulee.mvvmframework.BR;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.entity.GankEntity;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class RecyclerItemVM extends BaseVM implements ItemVar {

    public ObservableField<String> title = new ObservableField<>();

    public ObservableField<String> image = new ObservableField<>();

    public RecyclerItemVM(GankEntity entity) {
        this.title.set(entity.getDesc());
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
}