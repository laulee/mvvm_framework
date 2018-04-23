package com.framework.core.app;

import android.app.Application;

import com.framework.core.manager.AppManager;
import com.framework.core.network.VolleyUtil;
import com.framework.core.network.ssl.FakeX509TrustManager;

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
        //防止https证书过期
        FakeX509TrustManager.allowAllSSL();
        VolleyUtil.initConfig(this);
    }

    public BaseApplication getInstance() {
        return instance;
    }
}
