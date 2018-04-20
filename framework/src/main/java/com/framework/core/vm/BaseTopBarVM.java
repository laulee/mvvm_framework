package com.framework.core.vm;

import android.app.Activity;

import com.framework.core.base.BaseVM;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/20.
 */

public class BaseTopBarVM extends BaseVM {
    public TopBarVM topBarVM;

    public BaseTopBarVM(Activity activity) {
        this.topBarVM = new TopBarVM(activity);
    }
}
