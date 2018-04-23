package com.laulee.mvvmframework.vm;

import android.database.Observable;
import android.databinding.ObservableField;

import com.framework.core.base.BaseVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class IndexVM extends BaseVM {

    public ObservableField<String> title = new ObservableField<>("标题");
    public ObservableField<String> age  = new ObservableField<>();

    public IndexVM() {
    }
}
