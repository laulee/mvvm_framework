package com.laulee.mvvmframework.vm;

import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framework.core.base.BaseVM;
import com.laulee.mvvmframework.ui.IndexFragment;
import com.laulee.mvvmframework.ui.RecyclerViewActivity;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class IndexVM extends BaseVM {

    private Fragment fragment;
    public ObservableField<String> title = new ObservableField<>("标题");
    public ObservableField<String> age = new ObservableField<>();

    public IndexVM(Fragment indexFragment) {
        this.fragment = indexFragment;
    }

    public void showRecycler(View view) {
        Intent intent = new Intent(fragment.getActivity(), RecyclerViewActivity.class);
        fragment.getActivity().startActivity(intent);
    }
}
