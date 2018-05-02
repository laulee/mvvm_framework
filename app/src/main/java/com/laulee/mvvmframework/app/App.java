package com.laulee.mvvmframework.app;

import com.framework.core.app.BaseApplication;
import com.laulee.mvvmframework.BuildConfig;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public String getServer() {
        return "https://news-at.zhihu.com";
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
