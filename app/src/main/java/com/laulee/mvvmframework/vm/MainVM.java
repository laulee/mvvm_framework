package com.laulee.mvvmframework.vm;

import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.view.View;

import com.framework.core.base.BaseVM;
import com.laulee.mvvmframework.ui.IndexFragment;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public class MainVM extends BaseVM {

    public ObservableField<Fragment> fragment = new ObservableField<>();

    public MainVM() {
    }

    public void showIndexFragment(IndexFragment fragment){
        this.fragment.set(fragment);
    }

    public void indexOnclick(View view){

    }

    public void userOnclick(View view){

    }
}
