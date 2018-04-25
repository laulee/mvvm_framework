package com.laulee.mvvmframework.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laulee.mvvmframework.R;
import com.laulee.mvvmframework.databinding.FragmentMainIndexBinding;
import com.laulee.mvvmframework.vm.IndexVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/25.
 */

public class HomeFragment extends Fragment {

    private FragmentMainIndexBinding mainIndexBinding;
    private IndexVM indexVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainIndexBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_index, null, false);
        View view = mainIndexBinding.getRoot();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        indexVM = new IndexVM(this);
        mainIndexBinding.setViewModel(indexVM);
        indexVM.getData();
    }
}
