package com.framework.core.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public abstract class BaseFragment<CVB extends ViewDataBinding> extends Fragment {

    protected View rootView;
    protected CVB cvb;

    private boolean isFirstLoad = false;
    private boolean isPrepared = false;
    private boolean isFragmentVisible = false;
    /**
     * <pre>
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link BaseFragment#setForceLoad(boolean)}来让Fragment下次执行initData
     * </pre>
     */
    private boolean forceLoad = false;

    /**
     * 设置viewModel
     */
    protected abstract void setViewModel();

    /**
     * 布局文件
     *
     * @return
     */
    public abstract int getLayoutId();


    /**
     * 是否注册事件
     *
     * @return
     */
    protected boolean enableEventBus() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (enableEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isFirstLoad = true;
        if (cvb == null) {
            if (getLayoutId() > 0) {
                cvb = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
                setViewModel();
                isPrepared = true;
                if (!isHidden()) {
                    initData();
                }
            } else {
                throw new IllegalArgumentException("layout is not a inflate");
            }
        }
        return cvb.getRoot();
    }

    /**
     * 懒加载
     */
    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (isPrepared && isFragmentVisible) {
            if (forceLoad || isFirstLoad) {
                forceLoad = false;
                isFirstLoad = false;
                initData();
            }
        }
    }

    /**
     * 加载数据
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     */
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    protected void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    protected void onInvisible() {
        isFragmentVisible = false;
    }
}
