package com.laulee.mvvmframework.ui;

import com.framework.core.base.BaseFragment;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.FragmentMainIndexBinding;
import com.laulee.mvvmframework.vm.IndexVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class IndexFragment extends BaseFragment<FragmentMainIndexBinding> {
    IndexVM indexVM;

    @Override
    protected void setViewModel() {
        indexVM = new IndexVM();
        cvb.setViewModel(indexVM);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_index;
    }

    @Override
    protected void initData() {
        indexVM.age.set("20");
    }
}
