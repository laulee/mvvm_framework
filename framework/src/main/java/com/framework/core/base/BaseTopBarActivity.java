package com.framework.core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framework.core.R;
import com.framework.core.databinding.ActivityBaseTopBarBinding;
import com.framework.core.vm.BaseTopBarVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public abstract class BaseTopBarActivity<CVB extends ViewDataBinding> extends BaseActivity<CVB> {

    private ActivityBaseTopBarBinding topBarBinding;
    protected BaseTopBarVM baseTopBarVM;

    @Override
    protected void bindView() {
        topBarBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_top_bar);
        baseTopBarVM = new BaseTopBarVM(this);
        topBarBinding.setViewModel(baseTopBarVM);
        if (getLayoutId() > 0) {
            cvb = DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutId(), topBarBinding.mainContent, false);
            topBarBinding.mainContent.removeAllViews();
            topBarBinding.mainContent.addView(cvb.getRoot());
            setTopBar();
            setViewModel();
        } else {
            throw new IllegalArgumentException("layout is not a inflate");
        }
    }

    /**
     * 设置topbar
     */
    protected void setTopBar() {
    }
}
