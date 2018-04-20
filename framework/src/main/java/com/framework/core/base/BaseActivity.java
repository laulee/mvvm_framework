package com.framework.core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public abstract class BaseActivity<CVB extends ViewDataBinding> extends AppCompatActivity {

    protected CVB cvb;
    protected int time;// 灭屏时间

    /**
     * 设置viewModel
     */
    protected abstract void setViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        time = 60;
        super.onCreate(savedInstanceState);
        bindView();
        //是否注册eventBus事件
        if (enableEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 初始化databing绑定布局
     */
    protected void bindView() {
        if (getLayoutId() > 0) {
            cvb = DataBindingUtil.setContentView(this, getLayoutId());
            setViewModel();
        } else {
            throw new IllegalArgumentException("layout is not a inflate");
        }
    }

    /**
     * 是否注册事件
     *
     * @return
     */
    protected boolean enableEventBus() {
        return false;
    }

    /**
     * 获取布局文件id
     *
     * @return
     */
    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (enableEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
