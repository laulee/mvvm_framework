package com.framework.core.app;

import android.app.Application;

import com.framework.core.manager.AppManager;
import com.framework.core.network.VolleyUtil;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class BaseApplication extends Application {

    private BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppManager.initConfig();
        VolleyUtil.initConfig(this);
    }

    public BaseApplication getInstance() {
        return instance;
    }
}
