package com.laulee.mvvmframework.ui;

import android.support.v7.widget.RecyclerView;

import com.framework.core.base.BaseTopBarActivity;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.ActivityRecyclerViewBinding;
import com.laulee.mvvmframework.vm.RecyclerVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class RecyclerViewActivity extends BaseTopBarActivity<ActivityRecyclerViewBinding> {
    private RecyclerVM recyclerVM;

    @Override
    protected void setViewModel() {
        recyclerVM = new RecyclerVM();
        cvb.setViewModel(recyclerVM);
    }

    @Override
    protected void initData() {
        recyclerVM.getData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void setTopBar() {
        super.setTopBar();
        baseTopBarVM.topBarVM.setTitleTxt("RecyclerView");
        baseTopBarVM.topBarVM.setLeftLayout(R.string.top_left, R.drawable.back);
    }
}
