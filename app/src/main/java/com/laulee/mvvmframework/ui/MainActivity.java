package com.laulee.mvvmframework.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.framework.core.base.BaseTopBarActivity;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.ActivityMainBinding;
import com.laulee.mvvmframework.vm.MainVM;

public class MainActivity extends BaseTopBarActivity<ActivityMainBinding> {

    private MainVM mainVM;
    private IndexFragment indexFragment;

    @Override
    protected void setViewModel() {
        mainVM = new MainVM();
        cvb.setViewModel(mainVM);
    }

    @Override
    protected void initData() {
        indexFragment = new IndexFragment();
        mainVM.showIndexFragment(indexFragment);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setTopBar() {
        super.setTopBar();
        baseTopBarVM.topBarVM.setTitleTxt("首页");
    }
}