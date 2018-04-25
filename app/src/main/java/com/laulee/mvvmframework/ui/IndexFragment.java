package com.laulee.mvvmframework.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.framework.core.base.BaseFragment;
import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.app.App;
import com.laulee.mvvmframework.databinding.FragmentMainIndexBinding;
import com.laulee.mvvmframework.vm.IndexVM;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class IndexFragment extends BaseFragment<FragmentMainIndexBinding> {
    IndexVM indexVM;

    @Override
    protected void setViewModel() {
        indexVM = new IndexVM(this);
        cvb.setViewModel(indexVM);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_index;
    }

    @Override
    protected void initData() {
        indexVM.getData();
        handleTouchEvent();
    }

    /**
     * 触摸事件拦截, 解决ptrFragmeLayout与recyclerview滑动时header显示异常的问题
     */
    private void handleTouchEvent() {
        cvb.ptrMallContent.disableWhenHorizontalMove(true);
        // 初始化view
        cvb.ptrMallContent.setLastUpdateTimeRelateObject(cvb.ptrMallContent);
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(App.getInstance());
        cvb.ptrMallContent.setHeaderView(header);
        cvb.ptrMallContent.addPtrUIHandler(header);
        cvb.ptrMallContent.disableWhenHorizontalMove(true);
        cvb.ptrMallContent.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                RecyclerView.LayoutManager layoutManager = cvb.recylerView.getLayoutManager();
                if (layoutManager != null && layoutManager.getChildCount() != 0) {
                    int fistVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                    if (fistVisibleItemPosition != 0) {
                        return false;
                    }
                }
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                indexVM.getData();
                cvb.ptrMallContent.refreshComplete();
            }
        });
        //图片加载优化
        cvb.recylerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    Glide.with(IndexFragment.this).resumeRequests();
                } else {
                    Glide.with(IndexFragment.this).pauseRequests();
                }
            }
        });
    }
}
