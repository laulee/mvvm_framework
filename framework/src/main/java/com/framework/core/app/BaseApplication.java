package com.framework.core.app;

import android.app.Application;

import com.framework.core.manager.AppManager;
import com.framework.core.network.volley.VolleyUtil;
import com.framework.core.network.retrofit.RetrofitHelper;
import com.framework.core.network.volley.ssl.FakeX509TrustManager;
import com.framework.core.utils.CrashHandler;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/23.
 */

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppManager.initConfig();
        //防止https证书过期
        FakeX509TrustManager.allowAllSSL();
        VolleyUtil.initConfig(this);
        //初始化retrofit
        RetrofitHelper.initConfig(this);
        //初始化错误捕获
        CrashHandler.getInstance().init(this);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 获取基地址
     *
     * @return
     */
    public abstract String getServer();

    /**
     * 是否Debug
     *
     * @return
     */
    public abstract boolean isDebug();
}
